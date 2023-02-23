package com.develop.bookstore.domain.mail.application;

import com.develop.bookstore.domain.user.dto.EmailAuthDTO;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSendService {

    private final JavaMailSenderImpl mailSender;

    //인증메일 보내기
    public String sendAuthMail(EmailAuthDTO emailAuthDTO) {
        //6자리 난수 인증번호 생성
        String authKey = UUID.randomUUID().toString();

        //인증메일 보내기
        MimeMessage mail = mailSender.createMimeMessage();
        String mailContent = "<h1>[이메일 인증]</h1><br><p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>"
            + "<a href='http://localhost:8080/member/checkByKeyAuth="
            + emailAuthDTO.email() + "&authKey=" + authKey + "' target='_blenk'>이메일 인증 확인</a>";

        try {
            mail.setSubject("회원가입 이메일 인증 ", "utf-8");
            mail.setText(mailContent, "utf-8", "html");
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAuthDTO.email()));
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return authKey;
    }
}
