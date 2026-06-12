package com.mercureit.DebtCollectorBFF.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PhaseSegmentDto {


    private Long idPhase;

    @Size(max = 100)
    private String libelle_phase;

}