package zhulikov.project.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zhulikov.project.notificationservice.entity.Notification;
import zhulikov.project.notificationservice.enums.StatusType;
import zhulikov.project.notificationservice.repository.NotificationRepo;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationStatusService {

    private final NotificationRepo notificationRepo;

    public void markAsSent(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("notification not found"));
        notification.setStatusType(StatusType.SENT);
        notification.setSentAt(new Date());
        notificationRepo.save(notification);
        log.info("Status updated: id={}, status=SENT",notification.getId());
    }

    public void markAsFailed(Long notificationId,Exception error) {
        notificationRepo.findById(notificationId)
                .ifPresent(notification -> {
                    notification.setStatusType(StatusType.FAILED);
                    notification.setErrorMessage(error.getMessage());
                    notification.setRetryCount(notification.getRetryCount()+1);
                    notificationRepo.save(notification);
                });
    }
}
