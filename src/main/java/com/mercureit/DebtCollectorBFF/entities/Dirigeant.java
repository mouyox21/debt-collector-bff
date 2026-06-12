package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "dirigeant")
public class Dirigeant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dirigeant_seq")
    @SequenceGenerator(name = "dirigeant_seq", sequenceName = "dirigeant_id_dirigeant_seq", allocationSize = 1)
    @Column(name = "id_dirigeant")
    private Long idDirigeant;
    @Column(name = "date_debut")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date_debut;
    @Column(name = "date_fin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date_fin;
    @Size(max = 255)
    @Column(name = "qualite")
    private String qualite;
    @NotNull
    @Column(name = "nb_part", nullable = false)
    private int nb_part;
    @Column(name = "pourcentage")
    private Float pourcentage;

    private String nom;
    private String prenom;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_pmorale")
    @JsonIgnore
    private PMorale pMorale;

}
