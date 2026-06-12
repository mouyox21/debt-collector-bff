package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.dto.DeviseDto;
import com.mercureit.DebtCollectorBFF.projections.AgenceProjection;
import com.mercureit.DebtCollectorBFF.projections.DeviseProjection;
import com.mercureit.DebtCollectorBFF.services.devise.IDeviseService;
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
@RequestMapping(path = "/devise", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviseController {

    private static final Logger log = LoggerFactory.getLogger(DeviseController.class);

    @Autowired
    IDeviseService deviseService;

    //@RolesAllowed("admin")
//    @GetMapping("/listDevises")
//    public ResponseEntity<List<DeviseDto>> getAllDevises() {
//
//        List<DeviseDto> deviseDto = deviseService.getDevises();
//
//        return ResponseEntity.ok(deviseDto);
//    }

    @GetMapping("/devises")
    public List<DeviseProjection> getAllDevise() {
        return deviseService.getAllDevises();
    }

//    @GetMapping("/{id}")
//    public DeviseDto getDevise(@PathVariable Long id){
//        return deviseService.findDeviseById(id);
//    }


}
