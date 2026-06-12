package com.mercureit.DebtCollectorBFF.dto;

import com.mercureit.DebtCollectorBFF.entities.PhaseSegment;
import com.mercureit.DebtCollectorBFF.enumuration.EtatSegement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SegmentMaxAttDTO {
    private Integer idSegment;
    private String nomSegment;
    private String description;
    private EtatSegement etat;
    private String Abreviation;
    private PhaseSegment phaseSegment;

    private SegmentMaxAttDTO (
            Integer idSegment, String nomSegment,
            String description,EtatSegement etat,
            String Abreviation,PhaseSegment phaseSegment) {
        this.idSegment = idSegment;
        this.nomSegment = nomSegment;
        this.description = description;
        this.etat = etat;
        this.Abreviation = Abreviation;
        this.phaseSegment = phaseSegment;
    }
}
