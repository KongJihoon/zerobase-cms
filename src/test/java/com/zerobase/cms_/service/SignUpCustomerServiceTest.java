package com.zerobase.cms_.service;

import com.zerobase.cms_.domain.SignUpForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignUpCustomerServiceTest {

    @Autowired
    private SignUpCustomerService service;

    @Test
    void signup() {
        // given

        SignUpForm form = SignUpForm.builder()
                .name("name")
                .email("email")
                .password("password")
                .birthday(LocalDate.of(1997, 7, 24))
                .phone("010-4599-1719")
                .build();

        Assert.isTrue(service.signUp(form).getCustomerId() != null, "signup failed");

        // when

        // then

    }

}