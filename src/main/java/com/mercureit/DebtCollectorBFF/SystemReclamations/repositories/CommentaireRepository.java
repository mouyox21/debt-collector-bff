package com.mercureit.DebtCollectorBFF.SystemReclamations.repositories;

import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Commentaire;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
  List<Commentaire> findByClaim(Reclamation claim);
  List<Commentaire> findByEmployeeId(String employeeId);
}
