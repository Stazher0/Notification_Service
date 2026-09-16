package zhulikov.project.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import zhulikov.project.notificationservice.enums.NotificationType;
import zhulikov.project.notificationservice.enums.PriorityType;
import zhulikov.project.notificationservice.enums.StatusType;

import java.util.Date;

@Entity
@Data
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType; //выбирается тип уведомления email,sms,puth(tg)

    @Column(nullable = false)
    private String destination; //получатель 213@gmail.com или 88005353535

    private String theme; // тема письма --!!--

    @Column(nullable = false)
    private String content; //текст уведомления

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityType priorityType = PriorityType.MEDIUM; //приоритет уведомления - in default будет medium

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType statusType = StatusType.PENDING; // статус объекта in default в ожидании

    private Long templateId; //я хочу реализовать шаблоны, поэтому при его использовании это будет указываться

    @Column(nullable = false)
    private Integer retryCount=0; //счетчик попыток отправки

    @Column(length = 1000)
    private String errorMessage;

    @Column(nullable = false,updatable = false)
    private Date createdAt;
        //следовательно когда создано и когда отправлено
    @Column(nullable = true,updatable = true)
    private Date sentAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }
}
