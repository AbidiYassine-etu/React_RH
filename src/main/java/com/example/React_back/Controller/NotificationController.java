package com.example.React_back.Controller;

import com.example.React_back.Models.Notification;
import com.example.React_back.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/employee/{employeeId}")
    public List<Notification> getNotificationsEmployee(@PathVariable Long employeeId) {
        return notificationRepository.findByEmployeeId(employeeId);
    }
}