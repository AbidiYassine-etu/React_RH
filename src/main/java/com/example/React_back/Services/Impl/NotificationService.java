package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Employee;
import com.example.React_back.Models.Notification;
import com.example.React_back.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void createNotification(Employee employee, String action) {
        // Create and save the notification
        Notification notification = new Notification();
        notification.setMessage(employee.getNom() + " has created a " + action);
        notification.setTimestamp(LocalDateTime.now());
        notification.setEmployee(employee);
        notificationRepository.save(notification);

        // Send notification via WebSocket
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

    public void notifyEmployeeRequestChecked(Employee employee, String requestType) {
        // Notify the employee their request was checked
        Notification notification = new Notification();
        notification.setMessage("Your " + requestType + " request has been checked by adminRH.");
        notification.setTimestamp(LocalDateTime.now());
        notification.setEmployee(employee);
        notificationRepository.save(notification);

        // Send notification via WebSocket
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

    public void createNotificationEmp(Employee employee, String message) {
        // Logic to create and save a notification for the employee
        Notification notification = new Notification();
        notification.setEmployee(employee);
        notification.setMessage(message);
        notificationRepository.save(notification);

        // Send notification via WebSocket
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

}
