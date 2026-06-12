package com.mercureit.DebtCollectorBFF.dto;

import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.enumuration.StatusAffectation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioDAPDTO {

    private StatusAffectation statusAffectation;
    private Long idDossier;
    private Date dateDossier;
    private BigDecimal solde;
    private String categorieDossier;
    private String numIdentif;
    private String nomAgence;
    private String nomSegment;
    private String personneNom;

    public PortfolioDAPDTO(StatusAffectation statusAffectation, Long idDossier, Date dateDossier, BigDecimal solde, String categorieDossier, String numIdentif, String nomAgence, String nomSegment, String personneNom) {
        this.statusAffectation = statusAffectation;
        this.idDossier = idDossier;
        this.dateDossier = dateDossier;
        this.solde = solde;
        this.categorieDossier = categorieDossier;
        this.numIdentif = numIdentif;
        this.nomAgence = nomAgence;
        this.nomSegment = nomSegment;
        this.personneNom = personneNom;
    }

}
