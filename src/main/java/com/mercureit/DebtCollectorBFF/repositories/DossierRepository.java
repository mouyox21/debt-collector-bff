package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.entities.Dossier;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {

    @Query("""
    SELECT new com.mercureit.DebtCollectorBFF.dto.DossierDto(
        d.idDossier,
        d.codeOrigine,
        d.dateDossier,
        d.dateProvision,
        d.montantProvision,
        d.solde,
        d.categorieDossier,
        new com.mercureit.DebtCollectorBFF.dto.PersonnefordDto(
            p.id, p.type,
            CASE
                WHEN p.type = 'MORALE' THEN pm.nomLegal
                WHEN p.type = 'PHYSIQUE' THEN CONCAT(pp.nom, ' ', pp.prenom)
                ELSE NULL
            END,
            p.numIdentif
        ),
        new com.mercureit.DebtCollectorBFF.dto.AgenceDto(ag.idAgence, ag.nomAgence),
        new com.mercureit.DebtCollectorBFF.dto.SegmentDto(s.idSegment, s.nomSegment)
    )
    FROM Dossier d
    LEFT JOIN d.personne p
    LEFT JOIN d.agence ag
    LEFT JOIN d.segment s
    LEFT JOIN PPhysique pp ON p.id = pp.id
    LEFT JOIN PMorale pm ON p.id = pm.id
""")
    Page<DossierDto> findAllDossierProjections(Pageable pageable);




}
