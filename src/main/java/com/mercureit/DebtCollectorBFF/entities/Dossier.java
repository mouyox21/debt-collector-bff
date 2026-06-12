package com.mercureit.DebtCollectorBFF.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

import java.sql.Date;


import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "dossier")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dossier")
    private Long idDossier;

    @Size(max = 100)
    @Column(name = "code_origine", length = 100)

    private String codeOrigine;

    @NotNull
    @Column(name = "date_dossier")
//    @Temporal(TemporalType.DATE)
    private Date dateDossier;


    @Column(name = "date_provision")
    private Date dateProvision;

    @Column(name = "montant_provision")
    private BigDecimal montantProvision;

    @Column(name = "solde")
    @NotNull
    private BigDecimal solde;

    @Size(max = 255)
    @Column(name = "categorie_dossier")
    @NotNull
    private String categorieDossier;

    @ManyToOne
    @JoinColumn(name = "id_agence")
    @JsonIgnore
    private Agence agence;

    @ManyToOne
    @JoinColumn(name = "id_segment")
    @JsonIgnore
    private Segment segment;

    @ManyToOne
    @JoinColumn(name = "id_devise")
    @JsonIgnore
    private Devise devise;

//    @ManyToOne
//    @JoinColumn(name = "id_personne")
//    @JsonIgnore
//    private Personne personne;
    @ManyToOne
    @JoinColumn(name = "id_personne")
    @NotNull
    @JsonIgnore
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    @JsonIgnore
    private Agent agent;






    @OneToMany(mappedBy = "dossier")
    private List<ElementFinancier> elementFinanciers;

    @OneToMany(mappedBy = "dossier")
    private List<Garantie> garanties;

    @OneToMany(mappedBy = "dossier")
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "dossier")
    private List<Credit> credits;


    @OneToMany(mappedBy = "dossier")
    private List<PieceJointe> pieceJointes;


}
