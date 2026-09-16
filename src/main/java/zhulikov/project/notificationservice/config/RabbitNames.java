package zhulikov.project.notificationservice.config;

public final class RabbitNames {
    //определяем константы имен чтобы в будущем облегчить работу
    //определяем константы queue очередей
    public static final String EMAIL_HIGH_QUEUE = "email-high-queue";
    public static final String EMAIL_MEDIUM_QUEUE = "email-medium-queue";
    public static final String EMAIL_LOW_QUEUE = "email-low-queue";
    public static final String SMS_QUEUE = "sms-queue";
    public static final String PUSH_QUEUE = "push-queue";
    public static final String DEAD_NOTIFICATION_QUEUE = "dead-notification-queue";
    //определяем константы exchange обменников,посредников
    public static final String NOTIFICATION_EXCHANGE = "notification-exchange";
    public static final String DEAD_NOTIFICATION_EXCHANGE = "dead-notification-exchange";
    //определяем константы ключей для binding
    public static final String ROUTING_EMAIL_HIGH = "email.high";
    public static final String ROUTING_EMAIL_MEDIUM = "email.medium";
    public static final String ROUTING_EMAIL_LOW = "email.low";
    public static final String ROUTING_SMS = "sms";
    public static final String ROUTING_PUSH = "push";
    public static final String ROUTING_DEAD = "dead";

    //приватный конструктор чтобы не было варианта создать "new RabbitNames"
    private RabbitNames() {
        throw new AssertionError("Utility class");
    }
}
