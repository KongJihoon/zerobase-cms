package com.zerobase.cms_.service;

import com.zerobase.cms_.domain.SignUpForm;
import com.zerobase.cms_.domain.model.Customer;
import com.zerobase.cms_.exception.CustomException;
import com.zerobase.cms_.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.zerobase.cms_.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;






    public Customer signUp(SignUpForm form) {

        log.info("Sign up start");


        if (customerRepository.existsByEmail(form.getEmail())) {
            throw new CustomException(ALREADY_EXIST_EMAIL);
        }

        if (!checkPassword(form.getPassword(), form.getConfirmPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        if (!isValidPhoneNumber(form.getPhone())) {
            throw new CustomException(INVALID_PHONE_NUMBER);
        }

        Customer customer = Customer.from(form);

        // 패스워드 암호화
        customer.setPassword(passwordEncoder.encode(form.getPassword()));

        log.info("Sign up end");


        return customerRepository.save(customer);

    }


    private boolean checkPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isValidPhoneNumber(String phone) {
        // 휴대폰 번호가 01x-xxxx-xxxx 형식인지 체크
        String regex = "^01[0-9]-\\d{4}-\\d{4}$";
        return phone.matches(regex);
    }

    /**
     * 1. BaseEntity가 어떻게 동작하는가
     *
     * 2. 비밀번호에 대한 암호화
     *
     * 3. 핸드폰 번호 양식?
     */
}
