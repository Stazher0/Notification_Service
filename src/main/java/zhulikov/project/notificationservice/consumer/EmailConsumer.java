package zhulikov.project.notificationservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zhulikov.project.notificationservice.dto.EmailNotificationDto;
import zhulikov.project.notificationservice.enums.PriorityType;
import zhulikov.project.notificationservice.service.EmailSender;
import zhulikov.project.notificationservice.service.NotificationStatusService;

import static zhulikov.project.notificationservice.config.RabbitNames.EMAIL_HIGH_QUEUE;
import static zhulikov.project.notificationservice.config.RabbitNames.EMAIL_MEDIUM_QUEUE;
import static zhulikov.project.notificationservice.config.RabbitNames.EMAIL_LOW_QUEUE;

@Component
@Slf4j
@RequiredArgsConstructor
//КЛАСС ЗАБИРАЕТ message из queue
public class EmailConsumer {

    private final NotificationStatusService notificationStatusService;
    private final EmailSender  emailSender;

    private void processEmail(EmailNotificationDto dto, PriorityType priority) {
        Long notificationId = dto.getNotificationId();
        try {
            log.info("EMAIL {} priority received {}", priority, dto);
            emailSender.send(dto);
            notificationStatusService.markAsSent(notificationId);
        }catch (Exception error){
            notificationStatusService.markAsFailed(notificationId,error);
            throw new RuntimeException("Failed to process email: " + notificationId, error);
        }
    }

    @RabbitListener(queues = EMAIL_HIGH_QUEUE,concurrency = "5",messageConverter = "messageConverter")
    public void receiveHigh(EmailNotificationDto dto) {
        processEmail(dto,PriorityType.HIGH);
    }

    @RabbitListener(queues = EMAIL_MEDIUM_QUEUE,concurrency = "3",messageConverter = "messageConverter")
    public void receiveMedium(EmailNotificationDto dto) {
        processEmail(dto,PriorityType.MEDIUM);
    }

    @RabbitListener(queues = EMAIL_LOW_QUEUE,concurrency = "1",messageConverter = "messageConverter")
    public void receiveLow(EmailNotificationDto dto) {
        processEmail(dto,PriorityType.LOW);
    }
}
