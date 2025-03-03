package com.zerobase.cms_.dto;


import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter

public class VerifyMailRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String email;


    @NotBlank(message = "인증번호를 입력해주세요.")
    private String code;

}
