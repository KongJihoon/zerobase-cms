package com.zerobase.cms_.domain.model;


import com.zerobase.cms_.domain.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@AuditOverride(forClass = BaseEntity.class)

public class Customer extends BaseEntity  {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String passwordConfirm;

    private LocalDate birthday;

    private String phone;

    @Builder.Default
    private boolean emailAuth = false;


    public static Customer from(SignUpForm form) {

        return Customer.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(form.getPassword())
                .birthday(form.getBirthday())
                .phone(form.getPhone())
                .build();
    }

    public void verifyEmailAuth() {
        this.emailAuth = true;
    }



}
