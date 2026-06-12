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
public class CommentaireDTO {

    private Long id;
    private String content;
    private LocalDate timestamp;
    private Long claimId;
    private String employeeId;

    public CommentaireDTO(Long id, String content, LocalDate timestamp, Long claimId, String employeeId) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.claimId = claimId;
        this.employeeId = employeeId;
    }
}
