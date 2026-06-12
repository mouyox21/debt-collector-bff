package com.mercureit.DebtCollectorBFF.services.credit_service;

import com.mercureit.DebtCollectorBFF.entities.Credit;
import com.mercureit.DebtCollectorBFF.exception.CreditNotFoundException;
import com.mercureit.DebtCollectorBFF.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Service
@Transactional
public class CreditServiceImp implements CreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Credit save(Credit credit) {
        return this.creditRepository.save(credit);
    }

    @Override
    public Credit getCreditById(Long id) throws CreditNotFoundException {
        return this.creditRepository.findById(id).orElseThrow(() -> new CreditNotFoundException("Credit not found with id: " + id));
    }

    @Override
    public Credit updateCredit(Credit credit, Long id) throws CreditNotFoundException {
        Credit existingCredit = this.creditRepository.findById(id).orElseThrow(() -> new CreditNotFoundException("Credit not found with id: " + id));
        credit.setIdCredit(id);
        return this.creditRepository.save(credit);
    }

    @Override
    public Credit deleteCredit(Long id) throws CreditNotFoundException {
        Credit credit = this.creditRepository.findById(id).orElseThrow(() -> new CreditNotFoundException("Credit not found with id: " + id));
        this.creditRepository.delete(credit);
        return credit;
    }

    @Override
    public Page<Credit> listCredits(int page, int size) {
        return this.creditRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Credit> findCreditsByidDossier(BigInteger id, int page, int size) {
        return this.creditRepository.searchCreditByIdDossier(id, PageRequest.of(page, size));
    }

    @Override
    public Page<Credit> findCreditsByNumContrat(BigDecimal num_contrat, int page, int size) {
        return this.creditRepository.searchCreditByNumContrat(num_contrat, PageRequest.of(page, size));
    }

    @Override
    public Page<Credit> findCreditsByIdCreditAndNumContratAndCodeVendeur(Long id, BigInteger numContrat, BigInteger codeVendeur, int page, int size) {
        return this.creditRepository.findByIdAndNumContratAndCodeVendeur(id, numContrat, codeVendeur, PageRequest.of(page, size));
    }

    @Override
    public Page<Credit> findCreditsByIdCreditAndDateCreationAndCodeVendeur(Long id, Date dateCreation, BigInteger codeVendeur, int page, int size) {
        return this.creditRepository.findByIdAndDateCreationAndCodeVendeur(id, dateCreation, codeVendeur, PageRequest.of(page, size));
    }
}
