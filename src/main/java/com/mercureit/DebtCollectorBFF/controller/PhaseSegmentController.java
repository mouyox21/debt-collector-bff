//package com.mercureit.DebtCollectorBFF.controller;
//
//import com.mercureit.DebtCollectorBFF.dto.PhaseSegmentDto;
//import com.mercureit.DebtCollectorBFF.services.PhaseSegment.IPhaseSegmentService;
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
//@RequestMapping(path = "/phaseSegment", produces = MediaType.APPLICATION_JSON_VALUE)
//public class PhaseSegmentController {
//
//    private static final Logger log = LoggerFactory.getLogger(PhaseSegmentController.class);
//
//    @Autowired
//    IPhaseSegmentService phaseSegmentService;
//
//
//    //@RolesAllowed("admin")
//    @GetMapping("/phaseSegments-list")
//    public ResponseEntity<List<PhaseSegmentDto>> getAllPhaseSegments() {
//
//        List<PhaseSegmentDto> phaseSegmentDto = phaseSegmentService.getPhaseSegments();
//
//        return ResponseEntity.ok(phaseSegmentDto);
//    }
//
//
//    @GetMapping("/{id}")
//    public PhaseSegmentDto getPhaseSegment(@PathVariable Long id){
//        return phaseSegmentService.findPhaseSegmentById(id);
//    }
//
//
//
//
////    @GetMapping("/dossier/{idDossier}")
////    public ResponseEntity<List<PhaseSegmentDto>> getPhaseSegmentsByDossierId(@PathVariable int idDossier) {
////        List<PhaseSegmentDto> phaseSegmentDto = phaseSegmentService.getPhaseSegmentsByDossierId(idDossier);
////        return ResponseEntity.ok(phaseSegmentDto);
////    }
//
//
//
//
//
//
//
//
//}
