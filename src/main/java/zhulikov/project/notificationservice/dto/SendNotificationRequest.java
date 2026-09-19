package zhulikov.project.notificationservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import zhulikov.project.notificationservice.enums.NotificationType;
import zhulikov.project.notificationservice.enums.PriorityType;

@Data
@NoArgsConstructor
public class SendNotificationRequest {

    private NotificationType notificationType;
    private String destination;
    private String theme;
    private String content;
    private PriorityType priorityType =  PriorityType.MEDIUM;
}
