package com.example;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class MyProjectBackendApplicationTests {
    @Resource
    JavaMailSender sender;
    @Resource
    AmqpAdmin amqpAdmin;

    @Value("${spring.mail.username}")
    private String mail;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("hello");
        message.setText("hello");
        message.setTo("blackbox@zlib.se");
        message.setFrom(mail);
        sender.send(message);
    }

    // 测试rabbitmq
    @Test
    void createExchangeTest() {
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
        System.out.println("成功");
    }
}
