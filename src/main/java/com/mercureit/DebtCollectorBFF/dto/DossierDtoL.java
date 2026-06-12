//package com.mercureit.DebtCollectorBFF.dto;
//
//import com.mercureit.DebtCollectorBFF.entities.*;
//import jakarta.persistence.OneToMany;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//@Getter
//@Setter
//public class DossierDto {
//    private Long idDossier;
//
//    private String codeOrigine;
//
//    private Date dateDossier;
//    private Date dateProvision;
//
//
//
//    private Double montantProvision;
//    private Double solde;
//
//    private String categorieDossier;
//
//
////    private AgentDto agent;
////
////    private Personne personne;
////
////    private DeviseDto devise;
//////
////    private AgenceDto agence;
////
////    private SegmentDto segment;
//////
////
////
////     @OneToMany(mappedBy = "dossier")
////     private List<ElementFinancierDto> elementFinanciers;
////
////   @OneToMany(mappedBy = "dossier")
////    private List<GarantieDto> garanties;
//
////    @OneToMany(mappedBy = "dossier")
////    private List<PortfolioDto> portfolios;
////
////    private String statusAffectation;
//
//////    @OneToMany(mappedBy = "dossier")
////    private List<CreditDto> credits;
////
////    @OneToMany(mappedBy = "dossier")
////    private List<PieceJointeDto> pieceJointes;
//
//    public DossierDto(Long idDossier, String codeOrigine, Date dateDossier,
//                      Date dateProvision, Double montantProvision, Double solde,
//                      String categorieDossier
//
////            , AgentDto agent, Personne personne, DeviseDto devise, AgenceDto agence, SegmentDto segment, List<ElementFinancierDto> elementFinanciers, List<GarantieDto> garanties
//
//    ) {
//        this.idDossier = idDossier;
//        this.codeOrigine = codeOrigine;
//        this.dateDossier = dateDossier;
//        this.dateProvision = dateProvision;
//        this.montantProvision = montantProvision;
//        this.solde = solde;
//        this.categorieDossier = categorieDossier;
////        this.agent = agent;
////        this.personne = personne;
////        this.devise = devise;
////        this.agence = agence;
////        this.segment = segment;
////        this.elementFinanciers = elementFinanciers;
////        this.garanties = garanties;
//    }
//
//
//
//}
package com.mercureit.DebtCollectorBFF.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class DossierDtoL {

    private Long idDossier;

    private java.sql.Date dateDossier;

    private String codeOrigine;

    private BigDecimal solde;

    private AgenceDto agence;

    private SegmentDto segment;

    private PersonnefordDto personne;

    public DossierDtoL(Long idDossier,
                       java.sql.Date dateDossier,
                       String codeOrigine,
                       BigDecimal solde,
                       AgenceDto agence,
                       SegmentDto segment
            ,
                       PersonnefordDto personne
    ) {
        this.idDossier = idDossier;
        this.dateDossier = dateDossier;
        this.codeOrigine = codeOrigine;
        this.solde = solde;
        this.agence = agence;
        this.segment = segment;
        this.personne = personne;
    }
}