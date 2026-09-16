package zhulikov.project.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zhulikov.project.notificationservice.dto.PushNotificationDto;

import static zhulikov.project.notificationservice.config.RabbitNames.PUSH_QUEUE;

@Slf4j
@Component
//КЛАСС ЗАБИРАЕТ message из queue
public class PushConsumer {

    @RabbitListener(queues = PUSH_QUEUE,messageConverter = "messageConverter")
    public void receive(PushNotificationDto dto) {
        log.info("Push received {}",dto.toString());
    }
}
