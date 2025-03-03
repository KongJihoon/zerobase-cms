package com.zerobase.cms_.global.service;

import com.zerobase.cms_.domain.model.Customer;
import com.zerobase.cms_.exception.CustomException;
import com.zerobase.cms_.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

import static com.zerobase.cms_.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    private final RedisService redisService;

    private final CustomerRepository customerRepository;


    private static final int CODE_LENGTH = 6;

    private static final Long EMAIL_TOKEN_EXPIRATION = 600000L;

    private static final String EMAIL_PREFIX = "Email-Auth: ";


    public void sendAuthMail(String email) {

        String code = createRandomCode();

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        boolean exist = customerRepository.existsByEmail(email);

        if (!exist) {
            throw new CustomException(EMAIL_NOT_FOUND);
        }
        
        try {
            mimeMessageHelper.setTo(email);
            mimeMessage.setSubject("회원가입 이메일 인증 코드");
            
            String msg = "<div style='margin:20px'>"
                    + "<h1>회원가입 이메일 인증 메일 입니다.</h1>"
                    + "<br>"
                    + "<p>아래 코드를 입력해주세요.</p>"
                    + "<br>"
                    + "<div align='center' style='border:1px solid black; font-family:verdana';>"
                    + "<div style='font-size:130%'>"
                    + "CODE : <strong>" + code + "</strong><div><br/> "
                    + "</div>";
            
            mimeMessageHelper.setText(msg, true);
         
            mailSender.send(mimeMessage);
            redisService.setDataExpire(EMAIL_PREFIX + email, code, EMAIL_TOKEN_EXPIRATION);
            
        } catch (MessagingException e) {
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }


    }
    
    public void verifyEmail(String email, String code) {

        if (isVerify(email)) {
            throw new CustomException(INVALID_AUTH_CODE);
        }

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(EMAIL_NOT_FOUND));

        customer.verifyEmailAuth();

        customerRepository.save(customer);

        redisService.deleteData(EMAIL_PREFIX + email);

    }

    private boolean isVerify(String email) {
        String data = redisService.getData(EMAIL_PREFIX + email);

        if (data == null) {
            throw new CustomException(EMAIL_NOT_FOUND);
        }

        return email.equals(data);
    }

    private String createRandomCode() {

        Random random = new Random();

        StringBuilder code = new StringBuilder();

        while (code.length() < CODE_LENGTH) {

            code.append(random.nextInt(10));
        }

        return code.toString();

    }


}
