package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.hibernate.annotations.ColumnDefault;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit", nullable = false)
    private Long idCredit;
    // @Column(name = "contact_id")
    // private Long contact_id;
    @Column(name = "date_creation")
    private LocalDate dateCreation;
    @Column(name = "date_signature")
    private LocalDate dateSignature;
    @Column(name = "date_fin_reelle")
    private LocalDate dateFinReelle;
    @Size(max = 255)
    @Column(name = "devise_contrat")
    private String deviseContrat;
    @Column(name = "agence_financement", precision = 38)
    private BigInteger agenceFinancement;
    @Column(name = "code_vendeur", precision = 38)
    private BigInteger codeVendeur;
    @Size(max = 255)
    @Column(name = "nom_vendeur")
    private String nomVendeur;
    @Size(max = 255)
    @Column(name = "objet_financement")
    private String objetFinancement;
    @Column(name = "montant_finance", precision = 38, scale = 2)
    private BigDecimal montantFinance;
    @Column(name = "code_assurance", precision = 38)
    private BigInteger codeAssurance;
    @Size(max = 255)
    @Column(name = "type_remb")
    private String typeRemb;
    @Size(max = 255)
    @Column(name = "periodicite")
    private String periodicite;
    @Column(name = "montant_echeance", precision = 38)
    private BigInteger montantEcheance;
    @Column(name = "nombre_echeances", precision = 38)
    private BigInteger nombreEcheances;
    @Column(name = "date_prem_ech")
    private LocalDate datePremEch;
    @Size(max = 255)
    @Column(name = "type_interets")
    private String typeInterets;
    @Column(name = "date_dern_ech")
    private LocalDate dateDernEch;
    @Size(max = 255)
    @Column(name = "periodicite_interets")
    private String periodiciteInterets;
    @Column(name = "taux_interets", precision = 38, scale = 2)
    private BigDecimal tauxInterets;
    @Size(max = 255)
    @Column(name = "index_interets")
    private String indexInterets;
    @Column(name = "marge_interets", precision = 38, scale = 2)
    private BigDecimal margeInterets;
    @Size(max = 255)
    @Column(name = "periodicite_inter_ret")
    private String periodiciteInterRet;
    @Column(name = "taux_inter_retard", precision = 38, scale = 2)
    private BigDecimal tauxInterRetard;
    @Size(max = 255)
    @Column(name = "moyen_paiement")
    private String moyenPaiement;
    @Column(name = "date_premier_impaye")
    private LocalDate datePremierImpaye;
    @Size(max = 255)
    @Column(name = "titulaire_prelevement")
    private String titulairePrelevement;
    @Column(name = "motif_defaut_local", precision = 38)
    private BigInteger motifDefautLocal;
    @Column(name = "date_defaut_local")
    private LocalDate dateDefautLocal;
    @NotNull
    @Column(name = "nombre_reports", nullable = false)
    private Integer nombreReports;
    @Column(name = "num_contrat")
    private Long  numContrat;


    @ManyToOne
    @JoinColumn(name = "id_dossier")
    @JsonIgnore
    private Dossier dossier;

//    @ManyToOne
//    @JsonIgnore
//    private Produit produit;
    @OneToMany(mappedBy = "credit")
    @JsonIgnore
    private List<Produit> produitList;


    @Column(name = "date_fin_prevue")
    private LocalDate dateFinPrevue;



}
