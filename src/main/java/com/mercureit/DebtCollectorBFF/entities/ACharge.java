package com.mercureit.DebtCollectorBFF.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.enumuration.Logement;
import com.mercureit.DebtCollectorBFF.enumuration.TypeHabitat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "a_charge")
public class ACharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_a_charge", nullable = false)
    private Long idAcharge;
    @Column(name = "logement_depuis")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate logement_depuis;
    @NotNull
    @Column(name = "residence_secondaire", nullable = false)
    private boolean residence_secondaire;
    @Size(max = 255)
    @Column(name = "ville_res_sec")
    private String ville_res_sec;
    @NotNull
    @Column(name = "nbr_enf", nullable = false)
    private int nbr_enf;
    @NotNull
    @Column(name = "nbr_tier", nullable = false)
    private int nbr_tier;
    @Enumerated(EnumType.STRING)
    private Logement logement;
    @Enumerated(EnumType.STRING)
    private TypeHabitat type_habitat;



    @OneToOne
    @JoinColumn(name = "id_pphysique")
    @JsonIgnore
    private PPhysique pPhysique;
}
