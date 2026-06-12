package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.entities.Entite;
import com.mercureit.DebtCollectorBFF.projections.EntiteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntiteRepository extends JpaRepository<Entite, Long> {
    @Query("SELECT e.idEntite AS id, e.nomEntite AS nom FROM Entite e")
       List<EntiteProjection> findAllEntiteIdAndNom();
}
