package com.mercureit.DebtCollectorBFF.SystemReclamations.repositories;

import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.HistoriqueReclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueReclamationRepository extends JpaRepository<HistoriqueReclamation, Long> {
    List<HistoriqueReclamation> findByClaim(Reclamation claim);
}
