package com.example.article.service;

public interface PermissionService {

    /**
     * Grants permission for a user to access an article, linked to a specific order.
     * This method should be idempotent based on the orderId.
     *
     * @param userId    The ID of the user gaining permission.
     * @param articleId The ID of the article.
     * @param orderId   The ID of the order that triggered this permission grant (for idempotency).
     * @throws Exception if granting permission fails after checks.
     */
    void grantArticlePermission(Long userId, Long articleId, String orderId) throws Exception;

    /**
     * Checks if permission has already been granted based on the order ID.
     *
     * @param orderId The business order ID.
     * @return true if permission associated with this order exists, false otherwise.
     */
    boolean isPermissionGrantedForOrder(String orderId);


    /**
     * Checks if a specific user has permission to access a specific article.
     *
     * @param userId    The user ID.
     * @param articleId The article ID.
     * @return true if the user has permission, false otherwise.
     */
    boolean hasPermission(Long userId, Long articleId);
}