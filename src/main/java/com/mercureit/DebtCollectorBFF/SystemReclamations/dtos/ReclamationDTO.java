package com.mercureit.DebtCollectorBFF.SystemReclamations.dtos;

import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimCategory;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimPriority;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationDTO {
    private Long id;
    private String title;
    private String description;
    private ClaimStatus status;
    private ClaimPriority priority;
    private ClaimCategory claimCategory;
    private LocalDate createdDate;
    private LocalDate resolvedDate;
    private String createdBy;
    private String assignedTo;
    private String fileUrl;
    private List<HistoriqueReclamationDTO> history = new ArrayList<>();
    private List<CommentaireDTO> comments = new ArrayList<>();

    // Constructors
    public ReclamationDTO(Long id, String title, String description, ClaimStatus status, ClaimPriority priority, LocalDate createdDate, LocalDate resolvedDate, String createdBy, String assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
        this.resolvedDate = resolvedDate;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
    }
    public ReclamationDTO(Long  id , String title, LocalDate createdDate, String createdBy ,ClaimStatus status, ClaimPriority priority) {
        this.id=id;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public ReclamationDTO(Long id, String title, String description, ClaimStatus status, ClaimPriority priority, LocalDate createdDate, String createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }
}
