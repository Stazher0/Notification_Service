package zhulikov.project.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zhulikov.project.notificationservice.enums.PriorityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotificationDto {

    private Long notificationId;   // чтобы consumer обновил статус в БД
    private String destination;    // email-адрес получателя
    private String theme;          // тема письма
    private String content;        // тело письма
    private PriorityType priorityType;
}