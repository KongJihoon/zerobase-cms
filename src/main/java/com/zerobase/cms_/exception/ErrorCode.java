package com.zerobase.cms_.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUMBER("유효하지 않은 핸드폰 번호입니다.", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND("이메일을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    INVALID_AUTH_CODE("유효하지 않은 이메일 인증번호입니다.", HttpStatus.BAD_REQUEST),;



    private String message;
    private HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
