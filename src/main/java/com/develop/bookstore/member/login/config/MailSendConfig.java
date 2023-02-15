package com.develop.bookstore.member.login.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSendConfig {

    @Value("${spring.mail.username}")
    private static String sendEmailName;
    @Value("${spring.mail.password}")
    private static String sendEmailPw;

    @Bean(name="mailSender")
    public JavaMailSender getJavaMailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.debug", true);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(sendEmailName);
        mailSender.setPassword(sendEmailPw);
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

}
