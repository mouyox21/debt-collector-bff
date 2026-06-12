package com.mercureit.DebtCollectorBFF.services.credit_service;

import com.mercureit.DebtCollectorBFF.entities.Credit;
import com.mercureit.DebtCollectorBFF.exception.CreditNotFoundException;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface CreditService {
    Credit save(Credit credit);
    Credit getCreditById(Long id) throws CreditNotFoundException;
    Credit updateCredit(Credit credit, Long id) throws CreditNotFoundException;
    Credit deleteCredit(Long id) throws CreditNotFoundException;
    Page<Credit> listCredits(int page, int size);
    Page<Credit> findCreditsByidDossier(BigInteger id, int page, int size);
    Page<Credit> findCreditsByNumContrat(BigDecimal num_contrat, int page, int size);
    Page<Credit> findCreditsByIdCreditAndNumContratAndCodeVendeur(Long id, BigInteger numContrat, BigInteger codeVendeur, int page, int size);
    Page<Credit> findCreditsByIdCreditAndDateCreationAndCodeVendeur(Long id, Date dateCreation, BigInteger codeVendeur, int page, int size);
}
