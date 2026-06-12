package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.dto.*;
//import com.mercureit.DebtCollectorBFF.dto.DossierListDto;
//import com.mercureit.DebtCollectorBFF.projections.DossierProjection;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import com.mercureit.DebtCollectorBFF.repositories.personne.PersonneRepository;
import com.mercureit.DebtCollectorBFF.services.dossier.IDossierService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/dossier", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin("*")
public class DossierController {

    private static final Logger log = LoggerFactory.getLogger(DossierController.class);

    @Autowired
    IDossierService dossierService;

    PersonneRepository personneRepository;




    @GetMapping("/dossiers-list")
    public Page<DossierDtoL> getAllDossiers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Optional<Long> idDossier,
            @RequestParam Optional<Date> dateDossier,
            @RequestParam Optional<BigDecimal> solde,
            @RequestParam Optional<String> agence,
            @RequestParam Optional<String> segment,
            @RequestParam Optional<TypePersonne> type,
            @RequestParam Optional<String> numIdentif,
            @RequestParam Optional<String> search) {
        return dossierService.getAllDossiers(page, size, idDossier, dateDossier, solde, agence, segment, type, numIdentif, search);
    }

    @GetMapping("/dossier-list")
    public Page<DossierDto> getAllDossiers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return dossierService.getAllDossiersForDto(page, size);
    }



//  WORKING CODE
//    @GetMapping("/dossiers-list")
//    public ResponseEntity<Page<DossierDto>> getAllDossiers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam Optional<Long> idDossier,
//            @RequestParam Optional<Date> dateDossier,
//            @RequestParam Optional<BigDecimal> solde,
//            @RequestParam Optional<Integer> agence,
//            @RequestParam Optional<Integer> segment,
//            @RequestParam Optional<TypePersonne> type,
//            @RequestParam Optional<String> nom,
//            @RequestParam Optional<String> nomLegal,
//            @RequestParam Optional<String> num_Identif) {
//
//        Page<DossierDto> dossierPage = dossierService.getDossiers(
//                page, size,
//                idDossier, dateDossier,  solde, agence, segment,
//                type,nom, nomLegal, num_Identif);
//        return ResponseEntity.ok(dossierPage);
//    }


    @GetMapping("/{id}")
    public DossierDto getDossier(@PathVariable Long id) {
        return dossierService.findDossierById(id);
    }





    @PostMapping("/create-dossier")
    public ResponseEntity<DossierCreationDto> addDossier(@RequestBody DossierCreationDto dossierDto) {

        dossierDto.setCodeOrigine("AN03J45H");

        dossierDto.setAgent(new AgentDto());
        dossierDto.getAgent().setIdAgent(1);

        dossierDto.setAgence(new AgenceDto());
        dossierDto.getAgence().setIdAgence(1L);

        dossierDto.setSegment(new SegmentDto());
        dossierDto.getSegment().setIdSegment(1);

        // Set the dateDossier to the current date
        dossierDto.setDateDossier(new Date(System.currentTimeMillis()));

        DossierCreationDto createdDossier = dossierService.saveDossier(dossierDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDossier);
    }

    @PostMapping("/create-dossierD")
    public ResponseEntity<DossierDto> addDossier(@RequestBody DossierDto dossierDto) {

        dossierDto.setCodeOrigine("AN03J45H");

        dossierDto.setAgent(new AgentDto());
        dossierDto.getAgent().setIdAgent(1);

        dossierDto.setAgence(new AgenceDto());
        dossierDto.getAgence().setIdAgence(1L);

        dossierDto.setSegment(new SegmentDto());
        dossierDto.getSegment().setIdSegment(1);

        // Set the dateDossier to the current date
        dossierDto.setDateDossier(new Date(System.currentTimeMillis()));

        DossierDto createdDossier = dossierService.saveDossierD(dossierDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDossier);
    }



    @PutMapping("/update-dossier/{id}")
    public ResponseEntity<DossierDto> updateDossier(@PathVariable Long id, @RequestBody DossierDto dossierDto) {
        DossierDto updatedDossier = dossierService.updateDossier(id, dossierDto);
        if (updatedDossier != null) {
            return ResponseEntity.ok(updatedDossier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-dossier/{id}")
    public ResponseEntity<String> deleteDossier(@PathVariable Long id) {
        try {
            dossierService.deleteDossier(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            String constraintName = e.getMostSpecificCause().getMessage().split("constraint \"")[1].split("\"")[0];
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cannot delete dossier. It is still referenced in the " + constraintName + " table.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the dossier.");
        }
    }


    //Drools api
    @PostMapping("/process")
    public DossierDto processDossier(@RequestBody DossierDto dossier) {
        return dossierService.processDossierToSegment(dossier);
    }
}
