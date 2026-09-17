package com.zosh.repository;

import com.zosh.modal.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,
        Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByBusinessId(Long businessId);
}
