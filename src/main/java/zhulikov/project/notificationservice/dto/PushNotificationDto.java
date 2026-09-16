package zhulikov.project.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zhulikov.project.notificationservice.enums.PriorityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationDto {

    private Long notificationId;   // чтобы consumer обновил статус в БД
    private String destination;    // device token или user id
    private String theme;          // заголовок push-уведомления
    private String content;        // текст push-уведомления
}