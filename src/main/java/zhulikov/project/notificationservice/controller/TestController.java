package zhulikov.project.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhulikov.project.notificationservice.dto.EmailNotificationDto;
import zhulikov.project.notificationservice.dto.PushNotificationDto;
import zhulikov.project.notificationservice.dto.SmsNotificationDto;
import zhulikov.project.notificationservice.producer.EmailProducer;
import zhulikov.project.notificationservice.producer.PushProducer;
import zhulikov.project.notificationservice.producer.SmsProducer;

@Slf4j
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final EmailProducer emailProducer;
    private final SmsProducer smsProducer;
    private final PushProducer pushProducer;

    @PostMapping("/email")
    public String sendEmail(@RequestBody EmailNotificationDto dto) {
        log.info("TEST: sending email: {}", dto);
        emailProducer.send(dto);
        return "Email sent to exchange";
    }

    @PostMapping("/sms")
    public String sendSms(@RequestBody SmsNotificationDto dto) {
        log.info("TEST: sending sms: {}", dto);
        smsProducer.send(dto);
        return "SMS sent to exchange";
    }

    @PostMapping("/push")
    public String sendPush(@RequestBody PushNotificationDto dto) {
        log.info("TEST: sending push: {}", dto);
        pushProducer.send(dto);
        return "Push sent to exchange";
    }
}