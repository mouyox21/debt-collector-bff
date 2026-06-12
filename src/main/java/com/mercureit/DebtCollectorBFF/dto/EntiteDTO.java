package com.mercureit.DebtCollectorBFF.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public class EntiteDTO {
    @JsonProperty("id")
    private Long id;
    @Size(max = 255)
    @JsonProperty("nom")
    private String nom;
}
