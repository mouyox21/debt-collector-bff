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
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commentaire")
    private Long id;

    @Column(name = "contenu")
    private String content;


    @Column(name = "horodatage")
    private LocalDate timestamp;

    @ManyToOne
    @JoinColumn(name = "id_reclamation")
    private Reclamation claim;

    @Column(name = "id_employee")
    private String employeeId;

    public Commentaire(String content, LocalDate timestamp, Reclamation claim, String employeeId) {
        this.content = content;
        this.timestamp = timestamp;
        this.claim = claim;
        this.employeeId = employeeId;
    }
}
