package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.entities.Agence;
import com.mercureit.DebtCollectorBFF.projections.AgenceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgenceRepository extends JpaRepository<Agence, Long> {


@Query("SELECT " +
        " a.idAgence AS idAgence," +
        " a.nomAgence  AS nomAgence " +
        "FROM Agence  a")
List<AgenceProjection> findAllAgenceIdAndNom();

}