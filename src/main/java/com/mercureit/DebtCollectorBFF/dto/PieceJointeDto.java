package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.mercureit.DebtCollectorBFF.entities.Personne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PieceJointeDto {

    private Long idFile;
    private String nomFile;
    private String typeFile;
    private Long tailleFile;
    private LocalDate dateCreation;
    private String path;
    private String permissionAccess;
    private String statusFile;
    private String typepiecejoindre;

    private Personne personne;
    @JsonBackReference
    private DossierDto dossier;


}
