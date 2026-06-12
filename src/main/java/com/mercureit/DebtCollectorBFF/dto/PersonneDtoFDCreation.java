package com.mercureit.DebtCollectorBFF.dto;

import com.mercureit.DebtCollectorBFF.entities.Adresse;
import com.mercureit.DebtCollectorBFF.entities.Dirigeant;
import com.mercureit.DebtCollectorBFF.entities.Telephone;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class PersonneDtoFDCreation {

    private Long id;
    private String email;
    private Adresse adresse;
    private TypePersonne type;
    private String nom;
    private String prenom;
    private String nomLegal;
    private String num_Identif;  // Camel case to match query
    private String activite;

    private String paysConstruction;

    private LocalDate dateDeCreation;

    private String paysNaissance;

    private LocalDate dateDeNaissance;


    private Date dateCreation;
    private Telephone telephone;
    private Dirigeant dirigeant;

    public PersonneDtoFDCreation(Long id,
                                 TypePersonne type,
                                 String nom,
                                 String prenom,
                                 String nomLegal,
                                 String num_Identif,
                                 String activite,
                                 String email,
                                 String paysConstruction,
                                    LocalDate dateDeCreation,


                                 String paysNaissance,

                                 LocalDate dateDeNaissance,

                                 Adresse adresse,
                                 Telephone telephone,

                                 Date dateCreation,
                                 Dirigeant dirigeant) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.prenom = prenom;
        this.nomLegal = nomLegal;
        this.num_Identif = num_Identif;
        this.activite = activite;
        this.email = email;
        this.paysConstruction = paysConstruction;
        this.dateDeCreation = dateDeCreation;
        this.paysNaissance = paysNaissance;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.dateCreation = dateCreation;
        this.dirigeant = dirigeant;
    }
}