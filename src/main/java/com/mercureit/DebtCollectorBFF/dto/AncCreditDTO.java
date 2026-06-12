package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.entities.AncCredit;
import com.mercureit.DebtCollectorBFF.entities.Entite;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class AncCreditDTO {
    private Double emprunte;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finCredit;
    private String objetCredit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date1Echeance;
    private Double montant;
    private EntiteDTO entite;
}
