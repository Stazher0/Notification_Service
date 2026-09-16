package zhulikov.project.notificationservice.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import zhulikov.project.notificationservice.dto.EmailNotificationDto;

import static zhulikov.project.notificationservice.config.RabbitNames.NOTIFICATION_EXCHANGE;
import static zhulikov.project.notificationservice.config.RabbitNames.ROUTING_EMAIL_LOW;
import static zhulikov.project.notificationservice.config.RabbitNames.ROUTING_EMAIL_MEDIUM;
import static zhulikov.project.notificationservice.config.RabbitNames.ROUTING_EMAIL_HIGH;

@Slf4j
@Component
@RequiredArgsConstructor
//КЛАСС КЛАДЕТ EmailNotification в notification-exchange(Обменник)
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(EmailNotificationDto dto) {
        if (dto.getPriorityType() == null) {
            throw new IllegalArgumentException("priorityType must not be null");
        }

        String routingKey = switch (dto.getPriorityType()) {
            case HIGH -> ROUTING_EMAIL_HIGH;
            case MEDIUM -> ROUTING_EMAIL_MEDIUM;
            case LOW -> ROUTING_EMAIL_LOW;
            default -> throw new IllegalArgumentException(
                    "Unknown priority: " + dto.getPriorityType()
            );
        };

        rabbitTemplate.convertAndSend(NOTIFICATION_EXCHANGE, routingKey, dto);

        log.info("Email notification sent to exchange={} routingKey={} notificationId={} destination={}",
                NOTIFICATION_EXCHANGE, routingKey, dto.getNotificationId(), dto.getDestination());
    }
}