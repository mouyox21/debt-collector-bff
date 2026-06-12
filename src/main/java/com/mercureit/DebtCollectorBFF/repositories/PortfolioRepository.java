package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.dto.PortfolioDAPDTO;
import com.mercureit.DebtCollectorBFF.entities.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query("SELECT p FROM Portfolio p WHERE p.agent.idAgent = :idAgent")
    Page<Portfolio> findByAgentId(@Param("idAgent") int idAgent, Pageable pageable);

    @Query("SELECT new com.mercureit.DebtCollectorBFF.dto.PortfolioDAPDTO(p.statusAffectation, d.idDossier, d.dateDossier, d.solde, d.categorieDossier, d.personne.numIdentif, d.agence.nomAgence, d.segment.nomSegment, " +
            "CASE WHEN TYPE(d.personne) = PPhysique THEN CONCAT(pp.prenom, ' ', pp.nom) ELSE pm.nomLegal END) " +
            "FROM Portfolio p JOIN p.dossier d " +
            "LEFT JOIN PPhysique pp ON d.personne.id = pp.id " +
            "LEFT JOIN PMorale pm ON d.personne.id = pm.id " +
            "WHERE d.agent.idAgent = :idAgent AND " +
            "(CASE WHEN TYPE(d.personne) = PPhysique THEN CONCAT(pp.prenom, ' ', pp.nom) ELSE pm.nomLegal END LIKE %:search%)")
    Page<PortfolioDAPDTO> findDossiersWithAgentIdAndSearch(@Param("idAgent") Long idAgent, @Param("search") String search, Pageable pageable);

}
