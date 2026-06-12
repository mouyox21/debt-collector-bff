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
public class PersonnefordDto {

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
//    @Size(max = 255)
//    @Column(name = "numero_telephone")
//    private String numeroTelephone;
//    @Size(max = 255)
//    @Column(name = "ville")
//    private String ville;

    public PersonnefordDto(Long id, TypePersonne type, String combinedNameOrNomLegal, String num_Identif) {
        this.id = id;
        this.type = type;
        if (type == TypePersonne.PHYSIQUE) {
            // For physical persons, split the combined name into nom and prenom
            String[] nameParts = combinedNameOrNomLegal.split(" ");
            this.nom = nameParts[0];
            this.prenom = nameParts.length > 1 ? nameParts[1] : "";
        } else {
            // For moral persons, set the nomLegal field
            this.nomLegal = combinedNameOrNomLegal;
        }
        this.num_Identif = num_Identif;
    }






}
