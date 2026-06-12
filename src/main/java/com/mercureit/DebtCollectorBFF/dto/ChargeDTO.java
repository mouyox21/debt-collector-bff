package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.enumuration.Nature;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ChargeDTO{
    @Enumerated(EnumType.STRING)
    private Nature nature;
    private Integer quantieme;
    private BigDecimal montant;
}
