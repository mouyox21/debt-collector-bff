package com.mercureit.DebtCollectorBFF.SystemReclamations.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to); // Adresse email du destinataire
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    public void notifyAdmins(String subject, String text) {
        // Retrieve admin emails from your system
        String[] adminEmails = {"admin1@example.com", "admin2@example.com"};
        for (String email : adminEmails) {
            sendEmail(email, subject, text);
        }
    }

    public void notifyUser(String userEmail, String subject, String text) {
        sendEmail(userEmail, subject, text);
    }

    public void notifyAssignedUser(String assignedUserEmail, String subject, String text) {
        sendEmail(assignedUserEmail, subject, text);
    }
}
