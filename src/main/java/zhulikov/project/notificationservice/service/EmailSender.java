package zhulikov.project.notificationservice.service;

import zhulikov.project.notificationservice.dto.EmailNotificationDto;
import zhulikov.project.notificationservice.dto.SendNotificationRequest;

public interface EmailSender {
    void send(EmailNotificationDto dto);
}
