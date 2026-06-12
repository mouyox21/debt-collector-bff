package com.mercureit.DebtCollectorBFF.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anc_credit")
public class AncCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anc_credit", nullable = false)
    private Long idAncCredit;
    @Column(name = "emprunte")
    private Double emprunte;
    @Column(name = "fin_credit")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finCredit;
    @Size(max = 255)
    @Column(name = "objet_credit")
    private String objetCredit;
    @Column(name = "date_1_echeance")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date1Echeance;
    @Column(name = "montant")
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "id_personne")
    @JsonIgnore
    private Personne personne;
    @ManyToOne
    @JoinColumn(name = "id_entite")
    @JsonIgnore
    private Entite entite;

}
