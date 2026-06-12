package com.mercureit.DebtCollectorBFF.controller.personne;


import com.mercureit.DebtCollectorBFF.dto.PersonneDTO;
import com.mercureit.DebtCollectorBFF.dto.PersonneDtoFDCreation;
import com.mercureit.DebtCollectorBFF.entities.PMorale;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;
import com.mercureit.DebtCollectorBFF.services.personne.PersonneServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/personne")
public class PersonneController {
    @Autowired
    private PersonneServiceImp personneServiceImp;

    @GetMapping("/get-personne/ident/{num_Identif}")
    public List<PersonneDtoFDCreation> getPMoraleByNum_Identif(@PathVariable String num_Identif) throws PersonneNotFoundException {
        return personneServiceImp.getPersonneByNum_Identif(num_Identif);
    }





    @PostMapping("/create-Personne")
    public Personne AddPersonne(@RequestBody Personne personne) throws PersonneNotFoundException {
        return personneServiceImp.savePersonne(personne);
    }
/*
    @GetMapping("/list-Personne")
    public Page<PersonneDTO> listPersonnesMorales(@RequestParam(name="page",defaultValue = "0") int page,
                                                  @RequestParam(name="size",defaultValue = "5")int size) {
        return personneServiceImp.listPersonnes(page, size);
    }*/
    @GetMapping("/get-personne/{id}")
    public Personne getPMoraleById(@PathVariable Long id) throws PersonneNotFoundException {
        return personneServiceImp.getPersonneById(id);
    }
    @PutMapping("/update-Personne/{id}")
    public Personne updatePMorale(@RequestBody PMorale requestDTO, @PathVariable Long id) throws PersonneNotFoundException {
        return personneServiceImp.updatePersonne(requestDTO, id);
    }

    @DeleteMapping("/delete-Personne/{id}")
    public Personne deletePMorale(@PathVariable Long id) throws PersonneNotFoundException {
        return personneServiceImp.deletePersonne(id);
    }
    @GetMapping("/filterPersonnes")
    public Page<PersonneDTO> filterPersonnes(Pageable pageable,
                                             @RequestParam(required = false) Long refClient,
                                             @RequestParam(required = false) String type,
                                             @RequestParam(required = false) String nomRS,
                                             @RequestParam(required = false) String cinICE,
                                             @RequestParam(required = false) String telephone,
                                             @RequestParam(required = false) String ville) {
        return personneServiceImp.listPersonnes(pageable, refClient, type, nomRS, cinICE, telephone, ville);
    }



    }
