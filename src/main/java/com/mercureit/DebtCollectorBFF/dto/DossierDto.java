package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mercureit.DebtCollectorBFF.entities.*;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DossierDto {

    private Long idDossier;

    private String codeOrigine;

    private Date dateDossier;

    private Date dateProvision;



    private BigDecimal montantProvision;


    private BigDecimal solde;

    private String categorieDossier;



    private AgentDto agent;

    private Personne personne;

    private PersonnefordDto personnefordDto;

    private DeviseDto devise;

    private AgenceDto agence;

    private SegmentDto segment;

    public DossierDto(Long idDossier, String codeOrigine, Date dateDossier, Date dateProvision,
                      BigDecimal montantProvision, BigDecimal solde, String categorieDossier,
                      PersonnefordDto personne, AgenceDto agence, SegmentDto segment) {
        this.idDossier = idDossier;
        this.codeOrigine = codeOrigine;
        this.dateDossier = dateDossier;
        this.dateProvision = dateProvision;
        this.montantProvision = montantProvision;
        this.solde = solde;
        this.categorieDossier = categorieDossier;
        this.personnefordDto = personne;
        this.agence = agence;
        this.segment = segment;
    }



//        @OneToMany(mappedBy = "dossier")
    private List<ElementFinancierDto> elementFinanciers;
//
//    @OneToMany(mappedBy = "dossier")
    private List<GarantieDto> garanties;
//
//    @OneToMany(mappedBy = "dossier")
    private List<PortfolioDto> portfolios;
//
////    @OneToMany(mappedBy = "dossier")
//    private List<CreditDto> credits;
////
////    @OneToMany(mappedBy = "dossier")
//@JsonManagedReference
private List<PieceJointeDto> pieceJointes;




}
