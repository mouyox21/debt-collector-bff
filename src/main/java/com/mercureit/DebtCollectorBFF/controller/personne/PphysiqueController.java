package com.mercureit.DebtCollectorBFF.controller.personne;


import com.mercureit.DebtCollectorBFF.dto.PphysiqueDTO;

import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;
import com.mercureit.DebtCollectorBFF.services.personne.physique.PphysiqueServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/physique")
public class PphysiqueController {
    @Autowired
    private PphysiqueServiceImp pphysiqueService;

    @PostMapping("/create-Pphysique")
    public PphysiqueDTO AddPersonne(@RequestBody PphysiqueDTO pphysiqueDTO) throws PersonneNotFoundException {
        return pphysiqueService.savePphysique(pphysiqueDTO);
    }

}
