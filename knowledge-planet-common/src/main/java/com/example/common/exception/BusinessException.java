package com.example.common.exception;

import com.example.common.response.Code;
import lombok.Data;

@Data
public class BusinessException extends BaseException {

    private Integer code;
    private String message;

    public BusinessException(Code code) {
        super(code.getMsg());
        this.code = code.getCode();
        this.message = code.getMsg();
    }

    public BusinessException(Code code, String message) {
        super(message);
        this.code = code.getCode();
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}