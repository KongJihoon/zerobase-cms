package com.zerobase.cms_.controller;


import com.zerobase.cms_.domain.SignUpForm;
import com.zerobase.cms_.domain.model.Customer;
import com.zerobase.cms_.dto.VerifyMailRequest;
import com.zerobase.cms_.global.service.MailService;
import com.zerobase.cms_.service.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final SignUpCustomerService customerService;
    private final MailService mailService;



    @PostMapping("/customer/signup")
    public Customer signUp(@RequestBody SignUpForm form) {
        return customerService.signUp(form);
    }

    @PostMapping("/customer/mail")
    public ResponseEntity<?> sendEmail(@RequestParam String email) {

        mailService.sendAuthMail(email);

        return ResponseEntity.ok().body("이메일 전송 완료");
    }

    @PostMapping("/customer/verify")
    public ResponseEntity<?> verifyEmail(@RequestBody @Validated VerifyMailRequest request) {


        mailService.verifyEmail(request.getEmail(), request.getEmail());

        return ResponseEntity.ok().body("이메일 인증이 완료되었습니다.");
    }
}
