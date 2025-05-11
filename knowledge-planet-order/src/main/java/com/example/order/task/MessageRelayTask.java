package com.example.order.task;

import com.example.common.entity.MessageOutbox;
import com.example.order.mapper.MessageOutboxMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; // Import Transactional

import java.util.List;
import java.util.Map; // Import Map

@Slf4j
@Component
public class MessageRelayTask {

    @Autowired
    private MessageOutboxMapper messageOutboxMapper;

    @Autowired
    private StreamBridge streamBridge; // Used to send messages dynamically

    @Autowired
    private ObjectMapper objectMapper; // For deserializing payload if needed, or just passing string

    @Value("${app.message-relay.enabled:true}")
    private boolean relayEnabled;

    @Value("${app.message-relay.batch-size:50}")
    private int batchSize;

    private static final int MESSAGE_STATUS_PENDING = 0;
    private static final int MESSAGE_STATUS_SENT = 1;
    private static final int MESSAGE_STATUS_FAILED = 2; // Optional: Mark as failed after max retries
    private static final int MAX_RETRIES = 5; // Example: Max retries before marking as failed

    // Binding name from application.yml (output binding)
    private static final String OUTPUT_BINDING_NAME = "paymentSuccessOutput-out-0";

    @Scheduled(fixedDelayString = "${app.message-relay.fixed-delay:10000}") // Runs every 10 seconds by default
    @Transactional // Wrap the entire relay process in a transaction
    public void relayPendingMessages() {
        if (!relayEnabled) {
            // log.trace("Message relay task is disabled."); // Optional trace log
            return;
        }

        log.debug("Running message relay task...");
        List<MessageOutbox> pendingMessages = messageOutboxMapper.findPendingMessages(batchSize);

        if (pendingMessages.isEmpty()) {
            log.debug("No pending messages found in outbox.");
            return;
        }

        log.info("Found {} pending messages to relay.", pendingMessages.size());

        for (MessageOutbox msg : pendingMessages) {
            boolean sentSuccessfully = false;
            try {
                log.debug("Attempting to send messageId: {}, aggregateId: {}", msg.getMessageId(), msg.getAggregateId());

                // Deserialize payload string back into a Map or specific DTO if needed by StreamBridge/Binder
                // Or simply send the JSON string if the consumer expects it
                // For simplicity, let's assume the binder/consumer handles the JSON string correctly.
                // If you need to send a specific object type:
                // Object payloadObject = objectMapper.readValue(msg.getPayload(), Class.forName("com.example.common.dto." + msg.getEventType())); // Requires specific DTO class mapping
                // Message<?> springMessage = MessageBuilder.withPayload(payloadObject)

                // Sending the raw JSON payload string:
                Message<String> springMessage = MessageBuilder.withPayload(msg.getPayload())
                        .setHeader("messageId", msg.getMessageId()) // Optional: pass original message ID as header
                        .setHeader("eventType", msg.getEventType()) // Optional: pass event type as header
                        .build();

                // Send message via StreamBridge using the configured output binding name
                sentSuccessfully = streamBridge.send(OUTPUT_BINDING_NAME, springMessage);

                if (sentSuccessfully) {
                    log.info("Successfully sent messageId: {} for orderId: {} to topic: {}",
                            msg.getMessageId(), msg.getAggregateId(), msg.getDestination());
                    // Update status to SENT in the database within the transaction
                    messageOutboxMapper.updateStatusById(msg.getId(), MESSAGE_STATUS_SENT);
                } else {
                    // This case might be less common with reliable binders, but handle defensively
                    log.warn("StreamBridge returned false for messageId: {}. Will retry.", msg.getMessageId());
                    // Optionally increment retry count here if needed, even if no exception occurred
                    handleSendFailure(msg); // Use common failure handling
                }

            } catch (Exception e) {
                log.error("Failed to send messageId: {} (Attempt {}). Error: {}",
                        msg.getMessageId(), msg.getRetryCount() != null ? msg.getRetryCount() + 1 : 1, e.getMessage());
                handleSendFailure(msg); // Use common failure handling
                // The exception will cause the transaction to roll back for this message's update attempt
            }
        }
        log.debug("Message relay task finished.");
    }

    // Helper method to handle send failures
    private void handleSendFailure(MessageOutbox msg) {
        int currentRetries = msg.getRetryCount() != null ? msg.getRetryCount() : 0;
        if (currentRetries + 1 >= MAX_RETRIES) {
            log.error("Max retries ({}) reached for messageId: {}. Marking as FAILED.", MAX_RETRIES, msg.getMessageId());
            // Mark as permanently failed
            messageOutboxMapper.updateStatusAndIncrementRetry(msg.getId(), MESSAGE_STATUS_FAILED);
        } else {
            // Just increment retry count, status remains PENDING (implicitly handled by mapper method)
            // We don't update status here, just log the attempt. The message remains PENDING.
            log.warn("Incrementing retry count for messageId: {}", msg.getMessageId());
            // Optionally, update only the retry count if needed, but leaving it PENDING is usually fine
            // messageOutboxMapper.incrementRetryCount(msg.getId()); // Need to add this method if desired
        }
    }
}