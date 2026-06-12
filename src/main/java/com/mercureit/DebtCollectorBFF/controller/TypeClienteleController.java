package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.entities.TypeClientele;
import com.mercureit.DebtCollectorBFF.services.typeClientele.TypeClienteleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/typeClientele", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin("*")
public class TypeClienteleController {
    @Autowired
    private final TypeClienteleService typeClienteleService;
    @GetMapping("/listTypeClientele")
    public List<TypeClientele> getAllTypeClientele() {
        return typeClienteleService.getAllTypeClientele();
    }
}
