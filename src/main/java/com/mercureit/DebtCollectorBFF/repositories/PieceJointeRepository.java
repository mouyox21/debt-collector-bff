package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.dto.PieceJointeProjection;
import com.mercureit.DebtCollectorBFF.entities.PieceJointe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceJointeRepository extends JpaRepository<PieceJointe,Long> {
    @Query("SELECT pj.idFile as idFile, pj.nomFile as nomFile, pj.typeFile as typeFile, pj.tailleFile as tailleFile, " +
            "pj.dateCreation as dateCreation, pj.path as path, pj.permissionAccess as permissionAccess, " +
            "pj.statusFile as statusFile, pj.typePiecejoindre as typepiecejoindre FROM PieceJointe pj")
    Page<PieceJointeProjection> findAllPieceJointe(Pageable pageable);
//    Page<PieceJointe> findByDossierId(Long id, Pageable pageable);
    @Query("SELECT pj.idFile as idFile, pj.nomFile as nomFile, pj.typeFile as typeFile, pj.tailleFile as tailleFile, " +
        "pj.dateCreation as dateCreation, pj.path as path, pj.permissionAccess as permissionAccess, " +
        "pj.statusFile as statusFile, pj.typePiecejoindre as typepiecejoindre " +
        "FROM PieceJointe pj INNER JOIN pj.personne p WHERE p.id = :personneId")
    Page<PieceJointeProjection> findByPersonneId(@Param("personneId") Long personneId, Pageable pageable);

    @Query("SELECT pj.idFile as idFile, pj.nomFile as nomFile, pj.typeFile as typeFile, pj.tailleFile as tailleFile, " +
        "pj.dateCreation as dateCreation, pj.path as path, pj.permissionAccess as permissionAccess, " +
        "pj.statusFile as statusFile, pj.typePiecejoindre as typepiecejoindre " +
        "FROM PieceJointe pj INNER JOIN pj.dossier d WHERE d.idDossier = :dossierId")
    Page<PieceJointeProjection> findByDossierId(@Param("dossierId") Long personneId, Pageable pageable);
}
