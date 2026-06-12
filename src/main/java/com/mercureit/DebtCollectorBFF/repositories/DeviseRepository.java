package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.entities.Devise;
import com.mercureit.DebtCollectorBFF.projections.DeviseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {


    @Query("SELECT e.idDevise AS idDevise," +
            " e.nomDevise AS nomDevise," +
            " e.symboleDevise AS symboleDevise" +
            " FROM Devise e")
    List<DeviseProjection> findAllDevises();
}