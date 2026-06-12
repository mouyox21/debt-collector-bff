package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.dto.AgenceDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.projections.AgenceProjection;
import com.mercureit.DebtCollectorBFF.projections.EntiteProjection;
import com.mercureit.DebtCollectorBFF.services.IAgenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/agence", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgenceController {

    private static final Logger log = LoggerFactory.getLogger(AgenceController.class);

    @Autowired
    IAgenceService agenceService;

    //@RolesAllowed("admin")
    @GetMapping("/listAgence")
    public ResponseEntity<List<AgenceDto>> getAllAgences() {

        List<AgenceDto> agenceDto = agenceService.getAgences();

        return ResponseEntity.ok(agenceDto);
    }

    @GetMapping("/agences")
    public List<AgenceProjection> getAllAgence() {
        return agenceService.getAllAgences();
    }

//    @GetMapping("/{id}")
//    public AgenceDto getAgence(@PathVariable Long id){
//        return agenceService.findAgenceById(id);
//    }


}
