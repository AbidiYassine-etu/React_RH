package com.example.React_back.Repository;

import com.example.React_back.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByEmployeeId(Long employeeId);
    List<Notification> findByEmployeeIdAndMessageContaining(Long employeeId, String keyword);
}
