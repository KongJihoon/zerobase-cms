package com.zerobase.cms_.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

    private String email;

    private String name;

    private String password;

    private String confirmPassword;

    private LocalDate birthday;

    private String phone;







}
