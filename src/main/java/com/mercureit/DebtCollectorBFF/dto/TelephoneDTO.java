package com.mercureit.DebtCollectorBFF.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TelephoneDTO {
    private String numeroTelephone;
    private String operateurTelephonique;
    private String pays;
    private String proprietaireNumero;
    private String typeNumero;
    private String indicatifRegional;
}
