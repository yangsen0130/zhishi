package com.example.common.exception;


public class BaseException extends RuntimeException{
	
	public BaseException() {
		
	}
	
	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(Throwable cause) {
		super(cause);
	}
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Integer code, String message, Throwable cause) {
		super(message, cause);
	}
}
