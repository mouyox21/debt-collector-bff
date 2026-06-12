package com.mercureit.DebtCollectorBFF.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "garantie")
public class Garantie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('garantie_id_garantie_seq'")
    @Column(name = "id_garantie", nullable = false)
    private Long idGarantie;

    @Column(name = "agence_financement")
    private Integer agenceFinancement;

//    @Column(name = "contact_id", precision = 38)
//    private BigDecimal contactId;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "date_fin_reelle")
    private Date dateFinReelle;

    @Column(name = "date_main_levee")
    private Date dateMainLevee;

    @Column(name = "date_signature")
    private Date dateSignature;

    @Column(name = "echeance_garantie")
    private Date echeanceGarantie;

    @Column(name = "main_levee", precision = 38, scale = 2)
    private BigDecimal mainLevee;

    @Column(name = "montant_garantie", precision = 38, scale = 2)
    private BigDecimal montantGarantie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dossier")
    private Dossier dossier;

    @Column(name = "numero_garantie")
    private Long numeroGarantie;

    @Size(max = 255)
    @Column(name = "devise_contrat")
    private String deviseContrat;

    @Size(max = 255)
    @Column(name = "devise_garantie")
    private String deviseGarantie;

    @Size(max = 255)
    @Column(name = "statut")
    private String statut;

    @Column(name = "code_produit", length = Integer.MAX_VALUE)
    private String codeProduit;

//    @Column(name = "origine_id", length = Integer.MAX_VALUE)
//    private String origineId;

}