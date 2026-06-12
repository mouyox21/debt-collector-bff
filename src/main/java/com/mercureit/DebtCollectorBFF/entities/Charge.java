package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.mercureit.DebtCollectorBFF.enumuration.Nature;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


import java.math.BigDecimal;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "charge")
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_charge", nullable = false)
    private int idCharge;
    @Enumerated(EnumType.STRING)
    private Nature nature;
    @NotNull
    @Column(name = "quantieme", nullable = false)
    private Integer quantieme;
    @Column(name = "montant", precision = 38, scale = 2)
    private BigDecimal montant;
    @ManyToOne
    @JoinColumn(name = "id_personne")
    @JsonIgnore
    private Personne personne;




}
