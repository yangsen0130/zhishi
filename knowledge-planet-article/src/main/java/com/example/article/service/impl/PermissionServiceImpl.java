package com.example.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.article.mapper.UserArticlePermissionMapper;
import com.example.article.service.PermissionService;
import com.example.common.entity.UserArticlePermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
// Note: ServiceImpl is optional here if you don't need its default methods for UserArticlePermission directly
public class PermissionServiceImpl extends ServiceImpl<UserArticlePermissionMapper, UserArticlePermission> implements PermissionService {

    @Autowired
    private UserArticlePermissionMapper permissionMapper;

    @Override
    public boolean isPermissionGrantedForOrder(String orderId) {
        // Check if a record with this orderId already exists
        return permissionMapper.countByOrderId(orderId) > 0;
    }

    @Override
    public boolean hasPermission(Long userId, Long articleId) {
        // Check if a record exists for this user/article combination
        return permissionMapper.countByUserAndArticle(userId, articleId) > 0;
    }


    @Override
    @Transactional // Ensure atomicity of the permission grant
    public void grantArticlePermission(Long userId, Long articleId, String orderId) throws Exception {
        log.info("Attempting to grant permission for userId: {}, articleId: {}, orderId: {}", userId, articleId, orderId);

        // Idempotency Check (Double check, although listener should check first)
        if (isPermissionGrantedForOrder(orderId)) {
            log.warn("Permission for orderId: {} already granted. Skipping insertion.", orderId);
            return; // Already processed
        }

        UserArticlePermission permission = new UserArticlePermission();
        permission.setUserId(userId);
        permission.setArticleId(articleId);
        permission.setOrderId(orderId); // Store the order ID
        permission.setGrantTime(LocalDateTime.now());

        try {
            int inserted = permissionMapper.insert(permission);
            if (inserted > 0) {
                log.info("Successfully granted permission via orderId: {}", orderId);
            } else {
                // Should not happen if no exception, but log just in case
                log.error("Permission insertion returned 0 rows for orderId: {}", orderId);
                throw new Exception("Failed to grant permission (insert returned 0).");
            }
        } catch (DuplicateKeyException e) {
            // This handles the case where the unique constraint (on order_id or user_id/article_id) is violated.
            // This means another concurrent process or a retry already inserted the record.
            log.warn("Caught DuplicateKeyException for orderId: {}. Permission likely already granted concurrently or on retry. Assuming success.", orderId);
            // Consider this a success in terms of idempotency.
        } catch (Exception e) {
            log.error("Error granting permission for orderId: {}", orderId, e);
            throw e; // Re-throw other exceptions to signal failure to the MQ listener
        }
    }
}