package com.develop.bookstore.domain.mail.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailSendConfig {

    @Value("${spring.mail.username}")
    private String sendEmailName;
    @Value("${spring.mail.password}")
    private String sendEmailPw;
    @Value("${spring.mail.host}")
    private String sendEmailHost;
    @Value("${spring.mail.port}")
    private int sendEmailPort;

    @Bean(name = "mailSender")
    public JavaMailSenderImpl getJavaMailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.debug", true);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(sendEmailHost);
        mailSender.setPort(sendEmailPort);
        mailSender.setUsername(sendEmailName);
        mailSender.setPassword(sendEmailPw);
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setJavaMailProperties(properties);
//        mailSender.setProtocol("smtps"); 네이버로 할시 활성화

        return mailSender;
    }

}
