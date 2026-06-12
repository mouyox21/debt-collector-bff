package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.entities.Credit;
import com.mercureit.DebtCollectorBFF.exception.CreditNotFoundException;
import com.mercureit.DebtCollectorBFF.services.credit_service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@RestController
//@CrossOrigin("*")
public class CreditRestController {
    @Autowired
    private CreditService creditService;

    @GetMapping("/credits")
    public Page<Credit> getAllCredits(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "5") int size) {
        return this.creditService.listCredits(page, size);
    }

    @PostMapping("/create-credits")
    public Credit createCredit(@RequestBody Credit credit) throws CreditNotFoundException {
        System.out.println("mon Credit appartien de frontEnd---> : " + credit.toString()); // Ajoutez ce log
        return this.creditService.save(credit);
    }

    @GetMapping("/credits/{id}")
    public Credit getCreditById(@PathVariable Long id) throws CreditNotFoundException {
        return this.creditService.getCreditById(id);
    }

    @PutMapping("/credits/{id}")
    public Credit updateCredit(@PathVariable Long id, @RequestBody Credit credit) throws CreditNotFoundException {
        return this.creditService.updateCredit(credit, id);
    }

    @DeleteMapping("/credits/{id}")
    public Credit deleteCredit(@PathVariable Long id) throws CreditNotFoundException {
        return this.creditService.deleteCredit(id);
    }

    @GetMapping("/credits/searchByDossier")
    public Page<Credit> getCreditByIdDossier(@RequestParam(name = "ID_dossier") BigInteger id,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "20") int size) throws CreditNotFoundException {
        return this.creditService.findCreditsByidDossier(id, page, size);
    }

    @GetMapping("/credits/numero_Contrat")
    public Page<Credit> getCreditByNumContrat(@RequestParam(name = "num_contrat") BigDecimal num_contrat,
                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "20") int size) throws CreditNotFoundException {
        return this.creditService.findCreditsByNumContrat(num_contrat, page, size);
    }

    @GetMapping("/credits/searchByCriteria")
    public Page<Credit> findCreditsByIdAndNumContratAndCodeVendeur(
            @RequestParam(name = "idCredit", required = false) Long id,
            @RequestParam(name = "numContrat", required = false) BigInteger numContrat,
            @RequestParam(name = "codeVendeur", required = false) BigInteger codeVendeur,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws CreditNotFoundException {
        return creditService.findCreditsByIdCreditAndNumContratAndCodeVendeur(id, numContrat, codeVendeur, page, size);
    }

    @GetMapping("/credits/searchByDate")
    public Page<Credit> findCreditsByIdAndDateCreationAndCodeVendeur(
            @RequestParam(name = "idCredit", required = false) Long id,
            @RequestParam(name = "dateCreation", required = false) Date dateCreation,
            @RequestParam(name = "codeVendeur", required = false) BigInteger codeVendeur,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws CreditNotFoundException {
        return creditService.findCreditsByIdCreditAndDateCreationAndCodeVendeur(id, dateCreation, codeVendeur, page, size);
    }
}
