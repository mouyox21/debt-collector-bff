package com.mercureit.DebtCollectorBFF.dto;

import com.mercureit.DebtCollectorBFF.enumuration.EtatSegement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SegmentDto {
    private Integer idSegment;
    private String nomSegment;

    private SegmentDto (Integer idSegment, String nomSegment) {
        this.idSegment = idSegment;
        this.nomSegment = nomSegment;
    }
    // No-arg constructor is automatically provided by Lombok
}
