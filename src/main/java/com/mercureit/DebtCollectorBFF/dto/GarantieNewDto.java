package com.mercureit.DebtCollectorBFF.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter

public class GarantieNewDto {
    private Long id;
    private Long dossier;
    private Integer agenceFinancement;
    private BigDecimal contactId;
    private Date dateCreation;
    private Date dateFinReelle;
    private Date dateMainLevee;
    private Date dateSignature;
    private Date echeanceGarantie;
    private BigDecimal mainLevee;
    private BigDecimal montantGarantie;
    private Long numeroGarantie;
    private String deviseContrat;
    private String deviseGarantie;
    private String statut;
    private String codeProduit;
}
