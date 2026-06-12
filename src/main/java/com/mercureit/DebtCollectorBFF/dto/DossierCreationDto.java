package com.mercureit.DebtCollectorBFF.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class DossierCreationDto {

    private Long idDossier;
    private Date dateDossier;
    private BigDecimal solde;
    private String codeOrigine;
    private Date dateProvision;
    private BigDecimal montantProvision;
    private String categorieDossier;
    private AgenceDto agence;
    private SegmentDto segment;
    private PersonneForDossierCreationDto personne;
    private DeviseDto devise;
    private AgentDto agent;


}
