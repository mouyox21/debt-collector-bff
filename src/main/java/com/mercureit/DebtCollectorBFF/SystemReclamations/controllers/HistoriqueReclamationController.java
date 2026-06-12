package com.mercureit.DebtCollectorBFF.SystemReclamations.controllers;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.HistoriqueReclamationDTO;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.HistoriqueReclamation.HistoriqueReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historiqueReclamation")
@CrossOrigin
public class HistoriqueReclamationController {
    private final HistoriqueReclamationService historiqueReclamationService;

    @Autowired
    public HistoriqueReclamationController(HistoriqueReclamationService historiqueReclamationService) {
        this.historiqueReclamationService = historiqueReclamationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueReclamationDTO> getHistoriqueReclamationById(@PathVariable("id") Long id) {
        HistoriqueReclamationDTO historiqueReclamationDTO = historiqueReclamationService.getHistoriqueReclamationById(id);
        return ResponseEntity.ok(historiqueReclamationDTO);
    }
    @PostMapping
    public ResponseEntity<HistoriqueReclamationDTO> saveHistoriqueReclamation(@RequestBody HistoriqueReclamationDTO historiqueReclamationDTO) {
        HistoriqueReclamationDTO historiqueReclamation=historiqueReclamationService.saveHistoriqueReclamation(historiqueReclamationDTO);
        return new ResponseEntity<>(historiqueReclamation, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Page<HistoriqueReclamationDTO>> listHistoriqueReclamations (@RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "10") int size) {
        Page<HistoriqueReclamationDTO> historiques=historiqueReclamationService.listHistoriqueReclamations(page, size);
        return ResponseEntity.ok(historiques);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HistoriqueReclamationDTO> updateHistoriqueReclamation(@PathVariable("id") Long id,
                                                                                @RequestBody HistoriqueReclamationDTO historiqueReclamationDTO){
        HistoriqueReclamationDTO historique=historiqueReclamationService.updateHistoriqueReclamation(id,historiqueReclamationDTO);
        return ResponseEntity.ok(historique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriqueReclamation(@PathVariable("id") Long id) {
        historiqueReclamationService.deleteHistoriqueReclamation(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/claim/{id}")
    public ResponseEntity<List<HistoriqueReclamationDTO>> listHistoriqueReclamationsByClaim(@PathVariable("id") Long id){
        List<HistoriqueReclamationDTO> historiques=historiqueReclamationService.listHistoriqueReclamationsByClaim(id);
        return ResponseEntity.ok(historiques);
    }


}
