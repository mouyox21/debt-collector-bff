package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.entities.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
}
