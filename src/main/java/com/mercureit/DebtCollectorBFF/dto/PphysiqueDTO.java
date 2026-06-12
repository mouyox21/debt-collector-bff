package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercureit.DebtCollectorBFF.enumuration.TypeIdentifiant;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class PphysiqueDTO {
    private String cliStatut;
    private String nationalite;
    private String langue;
    @Enumerated(EnumType.STRING)
    private TypePersonne type;
    private String numIdentif;
    @Enumerated(EnumType.STRING)
    private TypeIdentifiant typeIdentifiant;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreation;
    private String csp;
    private String activite;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    private String intitule;
    private java.sql.Date dateDebut;
    private String paysNaissance;
    private String nom;
    private String prenom;
    private String nomJeuneFille;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeDeces;
    private String statusFamille;
    private String sex;
    private String profession;
    private String situationProfessionnelle;
    private String autrePrenom;
    private String anciennete;
    private String villeNaissance;
    private String idTypeClientele;
}
