package com.mercureit.DebtCollectorBFF.dto;



import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class ElementFinancierDto {

    private Long idElementFinancier;



//    private Long  dossier;


    private Double montantHt;
    private Double montantTtc;




    private Instant dateEcheance;
    private LocalDate dateSaisie;

    private String payeur;

    private String type;


    private String status;



}
