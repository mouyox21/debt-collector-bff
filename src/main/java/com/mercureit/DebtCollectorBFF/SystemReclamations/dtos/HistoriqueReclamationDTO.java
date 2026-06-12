package com.mercureit.DebtCollectorBFF.SystemReclamations.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HistoriqueReclamationDTO {
    private Long id;
    private LocalDate actionDate;
    private String action;
    private String details;
    private Long claimId;
    private String employeeId;

    public HistoriqueReclamationDTO(Long id, LocalDate actionDate, String action, String details, Long claimId, String employeeId) {
        this.id = id;
        this.actionDate = actionDate;
        this.action = action;
        this.details = details;
        this.claimId = claimId;
        this.employeeId = employeeId;
    }
}
