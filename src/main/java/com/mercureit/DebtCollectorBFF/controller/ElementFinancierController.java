//package com.mercureit.DebtCollectorBFF.controller;
//
//import com.mercureit.DebtCollectorBFF.dto.ElementFinancierDto;
//import com.mercureit.DebtCollectorBFF.services.ElementFinancier.IElementFinancierService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/elementFinancier", produces = MediaType.APPLICATION_JSON_VALUE)
//public class ElementFinancierController {
//
//    private static final Logger log = LoggerFactory.getLogger(ElementFinancierController.class);
//
//    @Autowired
//    IElementFinancierService elementFinancierService;
//
//
//
//    @GetMapping("/dossier/{idDossier}")
//    public ResponseEntity<List<ElementFinancierDto>> getElementFinanciersByDossierId(@PathVariable Long idDossier) {
//        List<ElementFinancierDto> elementFinancierDto = elementFinancierService.getElementFinanciersByDossierId(idDossier);
//        return ResponseEntity.ok(elementFinancierDto);
//    }
//
//
//
//
//
//
//
//    //@RolesAllowed("admin")
//    @GetMapping("/elementFinanciers-list")
//    public ResponseEntity<List<ElementFinancierDto>> getAllElementFinanciers() {
//
//        List<ElementFinancierDto> elementFinancierDto = elementFinancierService.getElementFinanciers();
//
//        return ResponseEntity.ok(elementFinancierDto);
//    }
//
//
//    @GetMapping("/{idElementFinancier}")
//    public ElementFinancierDto getElementFinancier(@PathVariable Long idElementFinancier){
//        return elementFinancierService.findElementFinancierById(idElementFinancier);
//    }
//
//
//}
