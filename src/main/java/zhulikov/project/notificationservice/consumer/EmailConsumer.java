package zhulikov.project.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zhulikov.project.notificationservice.dto.PushNotificationDto;

import static zhulikov.project.notificationservice.config.RabbitNames.EMAIL_HIGH_QUEUE;
import static zhulikov.project.notificationservice.config.RabbitNames.EMAIL_MEDIUM_QUEUE;
import static zhulikov.project.notificationservice.config.RabbitNames.EMAIL_LOW_QUEUE;

@Component
@Slf4j
//КЛАСС ЗАБИРАЕТ message из queue
public class EmailConsumer {

    @RabbitListener(queues = EMAIL_HIGH_QUEUE,concurrency = "5",messageConverter = "messageConverter")
    public void receiveHigh(PushNotificationDto dto) {log.info("SMS high priority received {}",dto.toString());}

    @RabbitListener(queues = EMAIL_MEDIUM_QUEUE,concurrency = "3",messageConverter = "messageConverter")
    public void receiveMedium(PushNotificationDto dto) {log.info("SMS medium priority received {}",dto.toString());}

    @RabbitListener(queues = EMAIL_LOW_QUEUE,concurrency = "1",messageConverter = "messageConverter")
    public void receiveLow(PushNotificationDto dto) {log.info("SMS low priority received {}",dto.toString());}
}
