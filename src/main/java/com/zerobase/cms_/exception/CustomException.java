package com.zerobase.cms_.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends AbstractException{

    private String message;

    private HttpStatus httpStatus;

    private ErrorCode errorCode;


    public CustomException(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
        this.errorCode = errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
