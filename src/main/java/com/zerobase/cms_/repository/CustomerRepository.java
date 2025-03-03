package com.zerobase.cms_.repository;

import com.zerobase.cms_.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    boolean existsByEmail(String email);


    Optional<Customer> findByEmail(String email);
}
