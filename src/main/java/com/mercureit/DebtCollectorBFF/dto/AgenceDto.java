
package com.mercureit.DebtCollectorBFF.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgenceDto {
    private Long idAgence;
    private String nomAgence;

    private AgenceDto(Long idAgence, String nomAgence) {
        this.idAgence = idAgence;
        this.nomAgence = nomAgence;
    }
    // No-arg constructor is automatically provided by Lombok
}
