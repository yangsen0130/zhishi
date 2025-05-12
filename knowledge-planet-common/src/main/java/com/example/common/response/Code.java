package com.example.common.response;

public enum Code {
    /**
     * 基础code码
     */
    SUCCESS(200, "OK"),

    SYSTEM_ERROR(-1, "系统异常，请稍后重试"),

    UID_WORK_ID_ERROR(500, "uid_work_id设置失败"),

    NAME_PASSWORD_ERROR(501, "账号名或登录密码不正确"),

    INITIALIZE_HANDLER_STRATEGY_NOT_EXIST(502, "初始化操作策略不存在"),

    // 通用业务异常
    PARAM_ERROR(1000, "参数错误"),
    UNAUTHORIZED(1001, "未授权操作"),
    FORBIDDEN(1002, "禁止访问"),
    RESOURCE_NOT_FOUND(1003, "资源不存在"),
    DATA_ALREADY_EXIST(1004, "数据已存在"),

    // 用户相关异常
    USER_NOT_EXIST(2000, "用户不存在"),
    USER_PASSWORD_ERROR(2001, "密码错误"),
    USER_ACCOUNT_LOCKED(2002, "账户已锁定"),
    USERNAME_ALREADY_EXIST(2003, "用户名已存在"),
    EMAIL_ALREADY_EXIST(2004, "邮箱已存在"),

    // 订单相关异常
    ORDER_NOT_EXIST(3000, "订单不存在"),
    ORDER_STATUS_ERROR(3001, "订单状态不正确"),
    ORDER_UNAUTHORIZED(3002, "无权操作此订单"),

    // 文章相关异常
    ARTICLE_NOT_EXIST(5000, "文章不存在或已删除"),
    ARTICLE_ACCESS_DENIED(5001, "无权访问此文章"),

    // 文件相关异常
    FILE_EMPTY(6000, "上传文件不能为空"),
    FILE_TYPE_ERROR(6001, "文件类型错误"),
    FILE_UPLOAD_ERROR(6002, "文件上传失败"),
    ;

    private final Integer code;
    private String msg = "";

    Code(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}