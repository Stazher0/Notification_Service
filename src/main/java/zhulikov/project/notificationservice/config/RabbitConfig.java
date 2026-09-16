package zhulikov.project.notificationservice.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static zhulikov.project.notificationservice.config.RabbitNames.*;

@Configuration
@EnableRabbit // убрать после тестов
public class RabbitConfig {

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, JacksonJsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    //создаём аргументы DLX в map для неотправленных писем
    private static final Map<String,Object> args =
            Map.of("x-dead-letter-exchange",DEAD_NOTIFICATION_EXCHANGE,"x-dead-letter-routing-key",ROUTING_DEAD);


    // (ОЧЕРЕДЬ)Создаем очередь (durable = true, очередь выживет при перезапуске)
    // я решил создать 3 очереди с разными приоритетами для EMAIL
    // и по одной для SMS и PUSH чтобы CONSUMER обрабатывали их с разной скоростью
    // +1 очередь для уведомлений которые не отправились

    @Bean
    public Queue emailHighQueue() {
        return new Queue(EMAIL_HIGH_QUEUE,true,false,false,args);
    }
    @Bean
    public Queue emailMediumQueue() {
        return new Queue(EMAIL_MEDIUM_QUEUE,true,false,false,args);
    }
    @Bean
    public Queue emailLowQueue() {
        return new Queue(EMAIL_LOW_QUEUE,true,false,false,args);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(SMS_QUEUE,true,false,false,args);
    }
    @Bean
    public Queue pushQueue() {
        return new Queue(PUSH_QUEUE,true,false,false,args);
    }

    @Bean
    public Queue deadNotificationQueue() {
        return new Queue(DEAD_NOTIFICATION_QUEUE,true,false,false);
    }

    // (ОБМЕННИК)Создаем Topic-обменник
    // 1 для всех очередей кроме DNQ, он использует отдельный DirectExchange, чтобы избежать зацикливания
    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(NOTIFICATION_EXCHANGE);
    }
    @Bean
    public DirectExchange deadNotificationExchange() { return new DirectExchange(DEAD_NOTIFICATION_EXCHANGE); }

    // (ПРИВЯЗКА ОЧЕРЕДЬ+ОБМЕННИК)Связываем очереди и обменники с ключом маршрутизации
    // каждому queue свой binding
    @Bean
    public Binding emailHighBinding(Queue emailHighQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(emailHighQueue)
                .to(notificationExchange)
                .with(ROUTING_EMAIL_HIGH);
    }
    @Bean
    public Binding emailMediumBinding(Queue emailMediumQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(emailMediumQueue)
                .to(notificationExchange)
                .with(ROUTING_EMAIL_MEDIUM);
    }
    @Bean
    public Binding emailLowBinding(Queue emailLowQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(emailLowQueue)
                .to(notificationExchange)
                .with(ROUTING_EMAIL_LOW);
    }
    @Bean
    public Binding smsBinding(Queue smsQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(smsQueue)
                .to(notificationExchange)
                .with(ROUTING_SMS);
    }
    @Bean
    public Binding pushBinding(Queue pushQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(pushQueue)
                .to(notificationExchange)
                .with(ROUTING_PUSH);
    }
    @Bean
    public Binding deadNotificationBinding(Queue deadNotificationQueue, DirectExchange deadNotificationExchange) {
        return BindingBuilder.bind(deadNotificationQueue)
                .to(deadNotificationExchange)
                .with(ROUTING_DEAD);
    }
}
