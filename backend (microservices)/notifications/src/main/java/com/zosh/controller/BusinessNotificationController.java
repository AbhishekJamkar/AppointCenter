package com.zosh.controller;

import com.zosh.mapper.NotificationMapper;
import com.zosh.modal.Notification;
import com.zosh.payload.dto.BookingDTO;
import com.zosh.payload.dto.NotificationDTO;
import com.zosh.service.NotificationService;
import com.zosh.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/notifications/business-owner")
@RequiredArgsConstructor
public class BusinessNotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;
    private final BookingFeignClient bookingFeignClient;

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByBusinessId(
            @PathVariable Long businessId) {
        List<Notification> notifications = notificationService
                .getAllNotificationsByBusinessId(businessId);
        List<NotificationDTO> notificationDTOS=notifications
                .stream()
                .map((notification)-> {
                    BookingDTO bookingDTO= bookingFeignClient
                            .getBookingById(notification.getBookingId()).getBody();
                    return notificationMapper.toDTO(notification,bookingDTO);
                }).collect(Collectors.toList());
        return ResponseEntity.ok(notificationDTOS);
    }
}
