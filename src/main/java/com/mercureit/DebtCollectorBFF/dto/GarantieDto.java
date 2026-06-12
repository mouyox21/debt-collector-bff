package com.mercureit.DebtCollectorBFF.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class GarantieDto {
    private Long idGarantie;
//    private String origineId;
//    private BigDecimal contactId;
    private Date dateCreation;
//    private Date dateSignature;
//    private Date dateFinReelle;
//    private String deviseContrat;
    private String codeProduit;
//    private Integer agenceFinancement;
//    private Long numeroGarantie;
    private BigDecimal montantGarantie;
//    private String deviseGarantie;
//    private Date echeanceGarantie;
//    private BigDecimal mainLevee;
    private Date dateMainLevee;
    private String statut;
//    private Long dossierId; // Reference to Dossier entity by its ID
}
