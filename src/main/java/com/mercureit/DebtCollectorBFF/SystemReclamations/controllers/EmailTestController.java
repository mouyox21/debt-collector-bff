package com.mercureit.DebtCollectorBFF.SystemReclamations.controllers;

import com.mercureit.DebtCollectorBFF.SystemReclamations.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-email")
@CrossOrigin
public class EmailTestController {

    private final EmailService emailService;

    @Autowired
    public EmailTestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendTestEmail(@RequestBody String toEmail) {
        String subject = "Test d'envoi d'e-mail";
        String message = "Ceci est un test d'envoi d'e-mail depuis votre application.";

        // Send the email using EmailService
        emailService.sendEmail(toEmail, subject, message);

        return ResponseEntity.status(HttpStatus.OK).body("E-mail envoyé avec succès à " + toEmail);
    }
//
//    @GetMapping("/current-user")
//    public String getCurrentUser(@AuthenticationPrincipal OidcUser user) {
//        return user.getEmail();
//    }

}
