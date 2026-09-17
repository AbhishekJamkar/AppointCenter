package com.zosh.controller;

import com.zosh.mapper.NotificationMapper;
import com.zosh.modal.Notification;
import com.zosh.payload.dto.BookingDTO;
import com.zosh.payload.dto.NotificationDTO;
import com.zosh.service.NotificationService;
import com.zosh.service.client.BookingFeignClient;
import com.zosh.service.impl.RealTimeCommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;
    private final BookingFeignClient bookingFeignClient;

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(
            @RequestBody Notification notification) {
        NotificationDTO createdNotification = notificationService
                .createNotification(notification);


        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(
            @PathVariable Long userId) {
        List<Notification> notifications = notificationService
                .getAllNotificationsByUserId(userId);

        List<NotificationDTO> notificationDTOS=notifications.stream().map((notification)-> {
            BookingDTO bookingDTO= bookingFeignClient
                    .getBookingById(notification.getBookingId()).getBody();
            return notificationMapper.toDTO(notification,bookingDTO);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(notificationDTOS);
    }



    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {
        List<Notification> notifications = notificationService
                .getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(
            @PathVariable Long notificationId) throws Exception {
        Notification updatedNotification = notificationService
                .markNotificationAsRead(notificationId);
        BookingDTO bookingDTO= bookingFeignClient
                .getBookingById(updatedNotification.getBookingId()).getBody();

        NotificationDTO notificationDTO= notificationMapper.toDTO(
                updatedNotification,
                bookingDTO
        );

        return ResponseEntity.ok(notificationDTO);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}
