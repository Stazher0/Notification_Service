package zhulikov.project.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zhulikov.project.notificationservice.dto.SmsNotificationDto;

import static zhulikov.project.notificationservice.config.RabbitNames.SMS_QUEUE;

@Slf4j
@Component
//КЛАСС ЗАБИРАЕТ message из queue
public class SmsConsumer {

    @RabbitListener(queues = SMS_QUEUE,messageConverter = "messageConverter")
    public void receive(SmsNotificationDto dto) {
        log.info("SMS received {}",dto.toString());
    }
}
