package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.dto.DossierDtoL;
import com.mercureit.DebtCollectorBFF.entities.Dossier;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import com.mercureit.DebtCollectorBFF.dto.DossierDtoL;
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
import java.util.Optional;



@Repository
public interface DossierLRepository extends JpaRepository<Dossier, Long>{

    @Query("""
    SELECT new com.mercureit.DebtCollectorBFF.dto.DossierDtoL(
        d.idDossier,
        d.dateDossier,
        d.codeOrigine,
        d.solde,
        new com.mercureit.DebtCollectorBFF.dto.AgenceDto(a.idAgence, a.nomAgence),
        new com.mercureit.DebtCollectorBFF.dto.SegmentDto(s.idSegment, s.nomSegment),
        new com.mercureit.DebtCollectorBFF.dto.PersonnefordDto(p.id, p.type,
            CASE
                WHEN p.type = 'MORALE' THEN pm.nomLegal
                WHEN p.type = 'PHYSIQUE' THEN CONCAT(pp.nom, ' ', pp.prenom)
                ELSE NULL
            END,
            p.numIdentif
        )
    )
    FROM Dossier d
    LEFT JOIN d.agence a
    LEFT JOIN d.segment s
    LEFT JOIN d.personne p
    LEFT JOIN PPhysique pp ON p.id = pp.id
    LEFT JOIN PMorale pm ON p.id = pm.id
    WHERE (:idDossier IS NULL OR d.idDossier = :idDossier)
      AND (:dateDossier IS NULL OR d.dateDossier = :dateDossier)
      AND (:solde IS NULL OR d.solde = :solde)
      AND (:agence IS NULL OR (a.nomAgence) = (:agence))
      AND (:segment IS NULL OR (s.nomSegment) = (:segment))
      AND (:type IS NULL OR p.type = :type)
      AND (LOWER(:num_Identif) IS NULL OR LOWER(p.numIdentif) LIKE CONCAT('%', :num_Identif, '%') OR p.numIdentif LIKE CONCAT('%', :num_Identif, '%')) 
      AND (COALESCE(:search, '') = ''
        OR (p.type = 'PHYSIQUE' AND LOWER(CONCAT(pp.nom, ' ', pp.prenom)) LIKE LOWER(CONCAT('%', :search, '%'))) 
        OR (p.type = 'MORALE' AND LOWER(pm.nomLegal) LIKE LOWER(CONCAT('%', :search, '%'))))
""")
    Page<DossierDtoL> findAllDossierProjections(
            @Param("idDossier") Optional<Long> idDossier,
            @Param("dateDossier") Optional<Date> dateDossier,
            @Param("solde") Optional<BigDecimal> solde,
            @Param("agence") Optional<String> agence,
            @Param("segment") Optional<String> segment,
            @Param("type") Optional<TypePersonne> type,
            @Param("num_Identif") Optional<String> numIdentif,
            @Param("search") Optional<String> search,
            Pageable pageable);















    //  WORKING CODE
// @Query("SELECT d FROM Dossier d " +
//            "JOIN d.personne p " +
//
//
//            "WHERE (:idDossier IS NULL OR d.idDossier = :idDossier) " +
//            "AND (:dateDossier IS NULL OR d.dateDossier = :dateDossier) " +
//
//            "AND (:solde IS NULL OR d.solde = :solde) " +
//            "AND (:agence IS NULL OR d.agence.agence = :agence) " +
//            "AND (:segment IS NULL OR d.segment.segment = :segment) " +
//            "AND (:type IS NULL OR d.personne.type = :type) " +
//            "AND (:nom IS NULL OR (p.type = 'PHYSIQUE' AND p.nom = :nom)) " +
//            "AND (:nomLegal IS NULL OR (p.type = 'MORALE' AND p.nomLegal = :nomLegal))"+
//            "AND (:num_Identif IS NULL OR d.personne.num_Identif = :num_Identif) "
//                                                                                    )
//    Page<Dossier> findDossiersWithFilters(
//            @Param("idDossier") Optional<Long> idDossier,
//            @Param("dateDossier") Optional<Date> dateDossier,
//
//            @Param("solde") Optional<BigDecimal> solde,
//            @Param("agence") Optional<Integer> agence,
//            @Param("segment") Optional<Integer> segment,
//            @Param("type") Optional<TypePersonne> type,
//            @Param("nom") Optional<String> nom,
//            @Param("nomLegal") Optional<String> nomLegal,
//            @Param("num_Identif") Optional<String> num_Identif,
//            Pageable pageable);
//
//
}
