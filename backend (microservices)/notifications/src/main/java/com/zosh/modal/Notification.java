package com.zosh.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private Boolean isRead= false;
    private String description;
    private Long userId;
    private Long bookingId;
    @jakarta.persistence.Column(name = "salon_id")
    private Long businessId;
    private LocalDateTime createdAt;
}
