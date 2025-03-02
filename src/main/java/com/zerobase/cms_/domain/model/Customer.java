package com.zerobase.cms_.domain.model;


import com.zerobase.cms_.domain.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Locale;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@AuditOverride(forClass = BaseEntity.class)
public class Customer extends BaseEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDate birthday;
    @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$", message = "올바른 휴대폰 번호 형식이 아닙니다.")
    private String phone;

    @Builder.Default
    private boolean emailAuth = false;


    public static Customer from(SignUpForm form) {

        return Customer.builder()
                .name(form.getName())
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .birthday(form.getBirthday())
                .phone(form.getPhone())
                .build();
    }

}
