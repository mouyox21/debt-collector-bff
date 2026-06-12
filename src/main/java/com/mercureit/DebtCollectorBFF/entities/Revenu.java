package com.mercureit.DebtCollectorBFF.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.enumuration.Origine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;


import java.time.LocalDate;
import java.util.Date;

@Data
@Entity

@Table(name = "revenu")
public class Revenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revenu", nullable = false)
    private Long idRevenu;

    @NotNull
    @Column(name = "mois_13", nullable = false)
    private Boolean mois13 = false;

    @NotNull
    @Column(name = "montant", nullable = false)
    private Double montant;
    @Enumerated(EnumType.STRING)
    @Column(name = "origine", columnDefinition = "origine")
    private Origine origineRevenu;

    @NotNull
    @Column(name = "montant_conjoint", nullable = false)
    private Double montantConjoint;

    @NotNull
    @Column(name = "quantieme", nullable = false)
    private Integer quantieme;

  /*  @Column(name = "date_maj")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateMaj;*/

    @ManyToOne
    @JoinColumn(name = "id_personne")
    @JsonIgnore
    private Personne personne;

}
