package zhulikov.project.notificationservice.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import zhulikov.project.notificationservice.dto.SmsNotificationDto;

import static zhulikov.project.notificationservice.config.RabbitNames.NOTIFICATION_EXCHANGE;
import static zhulikov.project.notificationservice.config.RabbitNames.ROUTING_SMS;

@Slf4j
@Component
@RequiredArgsConstructor
//КЛАСС КЛАДЕТ SmsNotification в notification-exchange(Обменник)
public class SmsProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(SmsNotificationDto dto) {
        rabbitTemplate.convertAndSend(NOTIFICATION_EXCHANGE, ROUTING_SMS, dto);

        log.info("Sms notification sent to exchange={} routingKey={} notificationId={} destination={}",
                NOTIFICATION_EXCHANGE, ROUTING_SMS, dto.getNotificationId(), dto.getDestination());
    }
}
