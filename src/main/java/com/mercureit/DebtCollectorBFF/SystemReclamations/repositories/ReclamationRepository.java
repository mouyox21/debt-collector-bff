package com.mercureit.DebtCollectorBFF.SystemReclamations.repositories;

import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimPriority;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByAssignedTo(String userId);
    List<Reclamation> findByStatus(ClaimStatus status);
    List<Reclamation> findByPriority(ClaimPriority priority);
    List<Reclamation> findByCreatedByOrderByIdDesc(String id);
    List<Reclamation> findByCreatedBy(String userId);

    @Query("SELECT r FROM Reclamation r " +
            "WHERE (:title IS NULL OR r.title ILIKE %:title%) " +
            "AND (:createdDate IS NULL OR r.createdDate = :createdDate) " +
            "AND (:createdBy IS NULL OR r.createdBy ILIKE %:createdBy%) " +
            "AND (:status IS NULL OR r.status = :status) " +
            "AND (:priority IS NULL OR r.priority = :priority)")
    Page<Reclamation> filterReclamations(Pageable pageable,
                                         @Param("title") String title,
                                         @Param("createdDate")  LocalDate createdDate,
                                         @Param("createdBy") String createdBy,
                                         @Param("status") ClaimStatus status,
                                         @Param("priority") ClaimPriority priority);
}
