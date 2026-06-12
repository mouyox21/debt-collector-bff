package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.entities.Credit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    @Query("SELECT c FROM Credit c WHERE c.dossier.idDossier = :id")
    Page<Credit> searchCreditByIdDossier(@Param("id") BigInteger id, Pageable pageable);

    @Query("SELECT c FROM Credit c WHERE c.numContrat = :num_contrat")
    Page<Credit> searchCreditByNumContrat(@Param("num_contrat") BigDecimal num_contrat, Pageable pageable);

    @Query("SELECT c FROM Credit c WHERE (:idCredit IS NULL OR c.idCredit = :idCredit) " +
            "AND (:numContrat IS NULL OR c.numContrat = :numContrat) " +
            "AND (:codeVendeur IS NULL OR c.codeVendeur = :codeVendeur)")
    Page<Credit> findByIdAndNumContratAndCodeVendeur(@Param("idCredit") Long id,
                                                     @Param("numContrat") BigInteger numContrat,
                                                     @Param("codeVendeur") BigInteger codeVendeur,
                                                     Pageable pageable);

    @Query("SELECT c FROM Credit c WHERE (:idCredit IS NULL OR c.idCredit = :idCredit) " +
            "AND (:dateCreation IS NULL OR c.dateCreation = :dateCreation) " +
            "AND (:codeVendeur IS NULL OR c.codeVendeur = :codeVendeur)")
    Page<Credit> findByIdAndDateCreationAndCodeVendeur(@Param("idCredit") Long id,
                                                       @Param("dateCreation") Date dateCreation,
                                                       @Param("codeVendeur") BigInteger codeVendeur,
                                                       Pageable pageable);
}
