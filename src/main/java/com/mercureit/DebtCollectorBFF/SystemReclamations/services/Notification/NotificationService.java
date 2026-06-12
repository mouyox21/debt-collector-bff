package com.mercureit.DebtCollectorBFF.SystemReclamations.services.Notification;

import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Notification;
import java.util.List;

public interface NotificationService {
     /**
      * Sends a notification to a user.
      * @param title the title of the notification
      * @param message the message content
      * @param sender the recipient's identifier
      * @return the created Notification
      */
     //Notification sendNotificationA(String title, String message, String recipient);

     Notification sendNotification(String title, String message,String sender );

     /**
      * Retrieves all notifications for a specific user.
      * @param recipient the recipient's identifier
      * @return a list of notifications for the user
      */
     List<Notification> getNotificationsForUser(String recipient);

     /**
      * Marks a notification as read.
      * @param notificationId the ID of the notification to mark as read
      */
     void markAsRead(Long notificationId);

      List<Notification> getAllNotifications();
}
