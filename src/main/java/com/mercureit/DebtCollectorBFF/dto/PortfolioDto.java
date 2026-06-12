package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.enumuration.StatusAffectation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class PortfolioDto {

    private int idPortfolio;

    private Date date_affectation;

    private AgentDto Agent;

    @JsonIgnore
    private DossierDto dossier;

    @Enumerated(EnumType.STRING)
    private StatusAffectation statusAffectation;



}
