package com.mercureit.DebtCollectorBFF.dto;

import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonneDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_personne")
    private TypePersonne type;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 255)
    @Column(name = "nom_legal")
    private String nomLegal;
    @Size(max = 255)
    @Column(name = "num_identif")
    private String num_Identif;
    @Size(max = 255)
    @Column(name = "numero_telephone")
    private String numeroTelephone;
    @Size(max = 255)
    @Column(name = "ville")
    private String ville;

    public PersonneDTO(Long id, TypePersonne type, String nom, String prenom, String nomLegal, String num_Identif, String numeroTelephone, String ville) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.prenom = prenom;
        this.nomLegal = nomLegal;
        this.num_Identif = num_Identif;
        this.numeroTelephone = numeroTelephone;
        this.ville = ville;
    }
}


