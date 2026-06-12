package com.mercureit.DebtCollectorBFF.dto;

import com.mercureit.DebtCollectorBFF.enumuration.StatusAffectation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioDossierDTO {

    private StatusAffectation statusAffectation;
    private Long idDossier;
    private Date dateDossier;
    private BigDecimal solde;
    private String categorieDossier;
    private String codeOrigine;
    private String nomAgence;
    private String nomSegment;

    public PortfolioDossierDTO(StatusAffectation statusAffectation, Long idDossier, Date dateDossier, BigDecimal solde, String categorieDossier, String codeOrigine, String nomAgence, String nomSegment) {
        this.statusAffectation = statusAffectation;
        this.idDossier = idDossier;
        this.dateDossier = dateDossier;
        this.solde = solde;
        this.categorieDossier = categorieDossier;
        this.codeOrigine = codeOrigine;
        this.nomAgence = nomAgence;
        this.nomSegment = nomSegment;
    }

}
