package zhulikov.project.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zhulikov.project.notificationservice.enums.PriorityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsNotificationDto {

    private Long notificationId;   // чтобы consumer обновил статус в БД
    private String destination;    // номер телефона, например +79991234567
    private String content;        // текст SMS
}