package com.mercureit.DebtCollectorBFF.SystemReclamations.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Long id;

    @Column(name = "titre")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "destinataire")
    private String recipient;

    @Column(name = "read")
    private boolean read;

    @Column(name = "horodatage")
    private LocalDate timestamp;
/*
    @ManyToOne
    @JoinColumn(name = "id_reclamation") // Foreign key column
    @JsonBackReference
    private Reclamation claim; // Reference to the Reclamation entity
*/
    // Constructor for creating Notification without a Reclamation
    public Notification(String title, String message, String recipient, boolean read, LocalDate timestamp) {
        this.title = title;
        this.message = message;
        this.recipient = recipient;
        this.read = read;
        this.timestamp = timestamp;
    }

    // Constructor for creating Notification with a Reclamation
    public Notification(String title, String message, String recipient, boolean read, LocalDate timestamp, Reclamation claim) {
        this.title = title;
        this.message = message;
        this.recipient = recipient;
        this.read = read;
        this.timestamp = timestamp;

    }
    public Notification(String title, String message, boolean read, LocalDate timestamp) {
        this.title = title;
        this.message = message;
        this.read = read;
        this.timestamp = timestamp;

    }
    public Notification(String title, String message, boolean read, LocalDate timestamp,String recipient) {
        this.title = title;
        this.message = message;
        this.read = read;
        this.timestamp = timestamp;
        this.recipient=recipient;


    }
}
