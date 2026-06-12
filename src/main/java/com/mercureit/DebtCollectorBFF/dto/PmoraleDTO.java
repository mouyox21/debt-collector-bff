package com.mercureit.DebtCollectorBFF.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercureit.DebtCollectorBFF.entities.*;
import com.mercureit.DebtCollectorBFF.enumuration.TypeIdentifiant;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PmoraleDTO {

    private String cliStatut;
//    private String nationalite;
    private String langue;
    private TypePersonne type;
    private String numIdentif;
    private TypeIdentifiant typeIdentifiant;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;
    private String csp;
    private String activite;
    private List<AdresseDTO> adresses=new ArrayList<>();;
    private List<TelephoneDTO> telephone=new ArrayList<>();;
    private List<PieceJointeDto> pieceJointes=new ArrayList<>();;
    private List<RevenuDTO> revenus=new ArrayList<>();;
    private List<ChargeDTO> charges=new ArrayList<>();;
    private List<AncCreditDTO> ancCredit=new ArrayList<>();;
    //-----------------------------------------
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeCreation;
    private String nomLegal;
    private String formeJuridique;
    private String paysConstitution;
    private String villeConstitution;
    private String nomEnseigne;
    private BigInteger nRcs;
    private BigInteger nIf;
    private BigInteger nombreSalaries;
    private String typeCommerce;
    private String typeSociete;
    private Long idEntite;
    private List<DirigeantDTO> dirigeants=new ArrayList<>();;

}
