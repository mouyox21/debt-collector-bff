package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.enumuration.Origine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RevenuDTO{
    private Boolean mois13 = false;
    private Double montant;
    private Origine origineRevenu;
    private Double montantConjoint;
    private Integer quantieme;
}
