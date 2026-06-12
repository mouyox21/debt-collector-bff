//package com.mercureit.DebtCollectorBFF.controller;
//
//import com.mercureit.DebtCollectorBFF.dto.TelephoneDto;
//import com.mercureit.DebtCollectorBFF.services.ITelephoneService;
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
//@RequestMapping(path = "/telephone", produces = MediaType.APPLICATION_JSON_VALUE)
//public class TelephoneController {
//
//    private static final Logger log = LoggerFactory.getLogger(TelephoneController.class);
//
//    @Autowired
//    ITelephoneService telephoneService;
//
//
//
//
//
//    @GetMapping("/personne/{idPersonne}")
//    public ResponseEntity<List<TelephoneDto>> getTelephonesByPersonneId(@PathVariable int idPersonne) {
//        List<TelephoneDto> telephoneDto = telephoneService.getTelephonesByPersonneId(idPersonne);
//        return ResponseEntity.ok(telephoneDto);
//    }
//
//
//
//
//
//
//
//    //@RolesAllowed("admin")
//    @GetMapping("/telephones-list")
//    public ResponseEntity<List<TelephoneDto>> getAllTelephones() {
//
//        List<TelephoneDto> telephoneDto = telephoneService.getTelephones();
//
//        return ResponseEntity.ok(telephoneDto);
//    }
//
//
//    @GetMapping("/{id}")
//    public TelephoneDto getTelephone(@PathVariable Long id){
//        return telephoneService.findTelephoneById(id);
//    }
//
//
//}
//
