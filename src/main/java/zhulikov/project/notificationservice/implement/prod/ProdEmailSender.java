package zhulikov.project.notificationservice.implement.prod;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import zhulikov.project.notificationservice.dto.EmailNotificationDto;
import zhulikov.project.notificationservice.service.EmailSender;

@Service
@Profile("prod")
public class ProdEmailSender implements EmailSender{

    @Override
    public void send(EmailNotificationDto dto) {

    }
}
