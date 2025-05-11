package com.example.article.listener;

import com.example.article.service.PermissionService;
import com.example.common.dto.PaymentSuccessEvent;
import com.fasterxml.jackson.core.JsonProcessingException; // Import for exception handling
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt; // Import RocketMQ MessageExt
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener; // Import annotation
import org.apache.rocketmq.spring.core.RocketMQListener; // Import interface
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; // Use @Component for listener

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "payment-success-topic", // Topic name from configuration
        consumerGroup = "article-service-consumer-group" // Consumer group from configuration
        // Optional: selectorExpression = "yourTag", // If using tags
        // Optional: consumeMode = ConsumeMode.CONCURRENTLY, // Default is CONCURRENTLY
        // Optional: messageModel = MessageModel.CLUSTERING // Default is CLUSTERING
)
public class PaymentSuccessListener implements RocketMQListener<MessageExt> { // Implement RocketMQListener<MessageExt>

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ObjectMapper objectMapper; // Inject ObjectMapper

    @Override
    public void onMessage(MessageExt message) { // Method signature for RocketMQListener
        String messageBody = new String(message.getBody()); // Get message body as string
        String msgId = message.getMsgId(); // Get RocketMQ message ID
        String keys = message.getKeys(); // Get business keys if set during send
        int reconsumeTimes = message.getReconsumeTimes(); // Get retry count

        log.info("Received message - ID: {}, Keys: {}, RetryCount: {}, Body: {}", msgId, keys, reconsumeTimes, messageBody);

        PaymentSuccessEvent event = null;
        try {
            // Deserialize the JSON string payload
            event = objectMapper.readValue(messageBody, PaymentSuccessEvent.class);
            log.info("Deserialized PaymentSuccessEvent: {}", event);

            // --- Idempotency Check ---
            // Check if this order has already been processed
            if (permissionService.isPermissionGrantedForOrder(event.getOrderId())) {
                log.warn("Idempotency check: Permission for order {} already granted. Acknowledging message.", event.getOrderId());
                // Message is implicitly acknowledged by RocketMQ Spring Boot Starter upon successful method return
                return;
            }

            // --- Grant Permission ---
            log.info("Processing permission grant for order {}", event.getOrderId());
            permissionService.grantArticlePermission(event.getUserId(), event.getArticleId(), event.getOrderId());

            log.info("Successfully processed permission grant for order {}", event.getOrderId());
            // Successful processing, message will be acknowledged

        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize message body to PaymentSuccessEvent. MsgId: {}, Body: {}", msgId, messageBody, e);
            // Cannot process, likely won't succeed on retry. Consider manual intervention or DLQ.
            // Throwing exception might lead to infinite retries if deserialization always fails.
            // Depending on policy, might just log and let it be acknowledged to avoid blocking queue.
            // For now, let's acknowledge to avoid blocking.
            return; // Acknowledge potentially poison pill message
        } catch (Exception e) {
            log.error("Error processing PaymentSuccessEvent for order {}. MsgId: {}. Error: {}",
                    (event != null ? event.getOrderId() : "N/A"), msgId, e.getMessage(), e);

            // --- Retry Logic ---
            // RocketMQ Spring Boot Starter handles retries based on application.yml (max-reconsume-times)
            // Throwing an exception signals failure and triggers a retry attempt.
            // If max retries are exceeded, the message goes to DLQ (if configured).
            log.warn("Throwing exception to trigger message retry (Attempt {})...", reconsumeTimes + 1);
            throw new RuntimeException("Failed to process payment success event, triggering retry.", e);
        }
    }
}