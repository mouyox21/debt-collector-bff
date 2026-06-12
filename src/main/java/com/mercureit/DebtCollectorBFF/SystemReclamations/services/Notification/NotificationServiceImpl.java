package com.mercureit.DebtCollectorBFF.SystemReclamations.services.Notification;

import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Notification;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.NotificationRepository;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ReclamationRepository reclamationRepository; // Inject ReclamationRepository
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   ReclamationRepository reclamationRepository,
                                   SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.reclamationRepository = reclamationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Notification sendNotification(String title, String message,String sender) {
        // Check if the Reclamation exists
        /*
        Optional<Reclamation> reclamationOptional = reclamationRepository.findById(reclamationId);
        if (reclamationOptional.isEmpty()) {
            throw new RuntimeException("Reclamation not found with ID: " + reclamationId);
        }

        Reclamation reclamation = reclamationOptional.get();

         */
        Notification notification = new Notification(title, message, false, LocalDate.now(), sender);
        Notification savedNotification = notificationRepository.save(notification);

        // Send real-time WebSocket message to all users (or a default destination)
        messagingTemplate.convertAndSend("/topic/notifications", savedNotification);

        return savedNotification;
    }
/*
    @Override
    public Notification sendNotificationA(String title, String message, String recipient, Long reclamationId) {
        // Check if the Reclamation exists
        Optional<Reclamation> reclamationOptional = reclamationRepository.findById(reclamationId);
        if (reclamationOptional.isEmpty()) {
            throw new RuntimeException("Reclamation not found with ID: " + reclamationId);
        }

        Reclamation reclamation = reclamationOptional.get();
        Notification notification = new Notification(title, message, recipient, false, LocalDate.now(), reclamation);
        Notification savedNotification = notificationRepository.save(notification);

        // Send real-time WebSocket message to the specific user
        messagingTemplate.convertAndSendToUser(recipient, "/queue/notifications", savedNotification);

        return savedNotification;
    }
*/
    @Override
    public List<Notification> getNotificationsForUser(String recipient) {
        return notificationRepository.findByRecipient(recipient);
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + notificationId));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
