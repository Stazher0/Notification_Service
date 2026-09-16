package zhulikov.project.notificationservice.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import zhulikov.project.notificationservice.dto.PushNotificationDto;

import static zhulikov.project.notificationservice.config.RabbitNames.NOTIFICATION_EXCHANGE;
import static zhulikov.project.notificationservice.config.RabbitNames.ROUTING_PUSH;

@Slf4j
@Component
@RequiredArgsConstructor
//КЛАСС КЛАДЕТ PushNotification в notification-exchange(Обменник)
public class PushProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(PushNotificationDto dto) {
        rabbitTemplate.convertAndSend(NOTIFICATION_EXCHANGE, ROUTING_PUSH, dto);

        log.info("Push notification sent to exchange={} routingKey={} notificationId={} destination={}",
                NOTIFICATION_EXCHANGE, ROUTING_PUSH, dto.getNotificationId(), dto.getDestination());
    }
}
