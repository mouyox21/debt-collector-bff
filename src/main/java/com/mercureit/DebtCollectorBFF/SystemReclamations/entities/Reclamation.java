package com.mercureit.DebtCollectorBFF.SystemReclamations.entities;

import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimCategory;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimPriority;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamation")
    private Long id;

    @Column(name = "titre")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ClaimStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priorite")
    private ClaimPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie")
    private ClaimCategory claimCategory;

    @Column(name = "date_creation")
    private LocalDate createdDate;

    @Column(name = "date_resol")
    private LocalDate resolvedDate;


    @Column(name = "cree_par")
    private String createdBy;

    @Column(name = "assigne_a")
    private String assignedTo;

    @Column(name = "fichier_url")
    private String fileUrl; // URL ou nom du fichier associé

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriqueReclamation> history = new ArrayList<>();

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commentaire> comments = new ArrayList<>();
/*
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Notification> notifications = new ArrayList<>();


 */
    // Constructors
    public Reclamation(String title, String description, ClaimStatus status, ClaimPriority priority, LocalDate createdDate, String createdBy, String assignedTo) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
    }
    public Reclamation(Long  id , String title, LocalDate createdDate, String createdBy ,ClaimStatus status, ClaimPriority priority) {
        this.id=id;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Reclamation(String title, String description, ClaimStatus status, ClaimPriority priority, LocalDate  createdDate, String createdBy) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }
}
