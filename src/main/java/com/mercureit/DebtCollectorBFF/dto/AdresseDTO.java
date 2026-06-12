package com.mercureit.DebtCollectorBFF.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdresseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOccupation;
    private Integer etage;
    private Integer nAppartement;
    private String adresse;
    private String codePostal;
    private String nomBatiment;
    private String pays;
    private String region;
    private String rue;
    private String situationOccupation;
    private String typeAdresse;
    private String ville;
}
