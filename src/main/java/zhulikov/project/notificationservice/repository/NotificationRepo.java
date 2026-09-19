package zhulikov.project.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhulikov.project.notificationservice.entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}
