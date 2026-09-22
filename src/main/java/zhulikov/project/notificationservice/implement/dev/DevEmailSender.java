package zhulikov.project.notificationservice.implement.dev;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import zhulikov.project.notificationservice.dto.EmailNotificationDto;
import zhulikov.project.notificationservice.service.EmailSender;

@Service
@Profile("dev")
@Slf4j
@RequiredArgsConstructor
public class DevEmailSender implements EmailSender{

    private final JavaMailSender javaMailSender;

    @Value("${app.mail.from}")
    private String fromValue;

    @Override
    public void send(EmailNotificationDto dto) {
        log.info("EMAIL SENDING to: {}", dto.getDestination());
        javaMailSender.send(createSimpleMailMessage(dto));
        log.info("Email sent: id={}", dto.getNotificationId());
    }

    private SimpleMailMessage createSimpleMailMessage(EmailNotificationDto dto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(fromValue);
        simpleMailMessage.setTo(dto.getDestination());
        simpleMailMessage.setSubject(dto.getTheme());
        simpleMailMessage.setText(dto.getContent());

        return simpleMailMessage;
    }
}
