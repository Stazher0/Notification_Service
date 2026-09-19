package zhulikov.project.notificationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhulikov.project.notificationservice.dto.SendNotificationRequest;
import zhulikov.project.notificationservice.service.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<Long> send(@Valid @RequestBody SendNotificationRequest request){
        Long notificationId = notificationService.send(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationId);
    }
}
