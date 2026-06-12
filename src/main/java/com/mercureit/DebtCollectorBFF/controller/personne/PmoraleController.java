package com.mercureit.DebtCollectorBFF.controller.personne;

import com.mercureit.DebtCollectorBFF.dto.PmoraleDTO;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;
import com.mercureit.DebtCollectorBFF.services.personne.morale.PmoraleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/morale")
public class PmoraleController {
    @Autowired
    private PmoraleServiceImp pmoraleServiceImp;

    @PostMapping("/create-Pmorale")
    public PmoraleDTO AddPmorale(@RequestBody PmoraleDTO pmoraleDTO) throws PersonneNotFoundException {
        return pmoraleServiceImp.savePmorale(pmoraleDTO);
    }
}
