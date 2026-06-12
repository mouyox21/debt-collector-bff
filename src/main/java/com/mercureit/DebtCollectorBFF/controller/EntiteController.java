package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.projections.EntiteProjection;
import com.mercureit.DebtCollectorBFF.services.entite.EntiteServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/entite", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin("*")
public class EntiteController {
    @Autowired
    private EntiteServiceImp entiteServiceImp;
    @GetMapping("/listEntite")
    public List<EntiteProjection> getAllEntite() {
      return entiteServiceImp.getAllEntites();
    }
}
