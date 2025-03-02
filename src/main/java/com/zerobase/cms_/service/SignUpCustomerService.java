package com.zerobase.cms_.service;

import com.zerobase.cms_.domain.SignUpForm;
import com.zerobase.cms_.domain.model.Customer;
import com.zerobase.cms_.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;



    public Customer signUp(SignUpForm form) {

        return customerRepository.save(Customer.from(form));

    }
}
