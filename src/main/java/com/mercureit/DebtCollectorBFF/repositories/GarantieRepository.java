package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.entities.Garantie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantieRepository extends JpaRepository<Garantie, Long> {
}
