package com.example.exception;

public class CustomException extends RuntimeException{

    private final Integer code;

    public CustomException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
