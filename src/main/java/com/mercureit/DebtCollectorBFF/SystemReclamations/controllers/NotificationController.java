package com.mercureit.DebtCollectorBFF.SystemReclamations.controllers;

import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Notification;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Endpoint to send a notification to a user.
     * @param title the title of the notification
     * @param message the message content
     * @param sender the recipient's identifier
     * @return the created Notification
     */
    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(
            @RequestParam String title,
            @RequestParam String message,
            @RequestParam String sender
           ) {

        Notification notification = notificationService.sendNotification(title, message, sender);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all notifications for a specific user.
     * @param recipient the recipient's identifier
     * @return a list of notifications for the user
     */
    @GetMapping("/user/{recipient}")
    public ResponseEntity<List<Notification>> getNotificationsForUser(@PathVariable String recipient) {
        List<Notification> notifications = notificationService.getNotificationsForUser(recipient);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }


    /**
     * Endpoint to mark a notification as read.
     * @param notificationId the ID of the notification to mark as read
     * @return a ResponseEntity indicating the result of the operation
     */
    @PatchMapping("/read/{notificationId}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }
}
