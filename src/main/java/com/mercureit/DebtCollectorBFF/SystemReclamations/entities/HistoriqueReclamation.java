package com.mercureit.DebtCollectorBFF.SystemReclamations.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HistoriqueReclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique_recl")
    private Long id;

    @Column(name = "action_date")
    private LocalDate actionDate;

    @Column(name = "action")
    private String action;

    @Column(name = "details")
    private String details;

    @ManyToOne
    @JoinColumn(name = "id_reclamation")
    private Reclamation claim;

    @Column(name = "id_employee")
    private String employeeId;

    public HistoriqueReclamation(LocalDate actionDate, String action, String details, Reclamation claim, String employeeId) {
        this.actionDate = actionDate;
        this.action = action;
        this.details = details;
        this.claim = claim;
        this.employeeId = employeeId;
    }
}
