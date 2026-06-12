package com.mercureit.DebtCollectorBFF.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "element_financier")
public class ElementFinancier {
    @Id
    @Column(name = "id_element_financier", nullable = false)
    private Long idElementFinancier;

    @Column(name = "montant_ht")
    private Double montantHt;

    @Column(name = "montant_ttc")
    private Double montantTtc;

    @Column(name = "date_echeance")
    private Instant dateEcheance;

    @Column(name = "date_saisie")
    private LocalDate dateSaisie;

    @Size(max = 255)
    @Column(name = "payeur")
    private String payeur;

    @Size(max = 255)
    @Column(name = "status")
    private String status;

    @Size(max = 255)
    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dossier")
    private Dossier dossier;

}