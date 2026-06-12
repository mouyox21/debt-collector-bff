package com.mercureit.DebtCollectorBFF.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class CreditDto {
    private Long idCredit;
    private Long contactId;
    private Date dateCreation;
    private Date dateSignature;
//    private Date dateFinReelle;
//    private String deviseContrat;
//    private BigInteger agenceFinancement;
//    private BigInteger codeVendeur;
//    private String nomVendeur;
//    private String objetFinancement;
//    private BigDecimal montantFinance;
//    private BigInteger codeAssurance;
//    private String typeRemb;
//    private String periodicite;
//    private BigInteger montantEcheance;
//    private BigInteger nombreEcheances;
//    private Date datePremEch;
//    private String typeInterets;
//    private Date dateDernEch;
//    private String periodiciteInterets;
//    private BigDecimal tauxInterets;
//    private String indexInterets;
//    private BigDecimal margeInterets;
//    private String periodiciteInterRet;
//    private BigDecimal tauxInterRetard;
//    private String moyenPaiement;
//    private Date datePremierImpaye;
//    private String titulairePrelevement;
//    private BigInteger motifDefautLocal;
//    private Date dateDefautLocal;
//    private Integer nombreReports;
//    private Long numContrat;
//    private Long dossierId; // Reference to Dossier entity by its ID
//    private Long produitId; // Reference to Produit entity by its ID
}
