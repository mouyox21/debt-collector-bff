package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.dto.GarantieNewDto;
import com.mercureit.DebtCollectorBFF.entities.Garantie;
import com.mercureit.DebtCollectorBFF.repositories.GarantieRepository;
import com.mercureit.DebtCollectorBFF.services.Garantie.GarantieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

@RestController
@RequestMapping("/garanties")
public class GarantieController {

    @Autowired
    private GarantieRepository garantieRepository;
    @Autowired
    private GarantieService garantieService;


    private static final Logger logger = LoggerFactory.getLogger(GarantieController.class);


    @GetMapping("/garantie-list")
    public ResponseEntity<Page<GarantieNewDto>> getAllGaranties(int page, int size) {
        Page<Garantie> garanties = garantieService.getAllGaranties(page,size);

        Page<GarantieNewDto> garantieDTOS = garanties.map(this::mapToDTO);

        return ResponseEntity.ok(garantieDTOS);}



    private GarantieNewDto mapToDTO(Garantie garantie) {
        GarantieNewDto dto = new GarantieNewDto();
        dto.setId(garantie.getIdGarantie());
        if (garantie.getDossier() != null) {
            dto.setDossier(garantie.getDossier().getIdDossier()); // Assurez-vous d'utiliser l'ID du Dossier
        }
        dto.setAgenceFinancement(garantie.getAgenceFinancement());
//        dto.setContactId(garantie.getContactId());
        dto.setDateCreation(garantie.getDateCreation());
        dto.setDateSignature(garantie.getDateSignature());
        dto.setMontantGarantie(garantie.getMontantGarantie());
        dto.setDeviseGarantie(garantie.getDeviseGarantie());
        dto.setEcheanceGarantie(garantie.getEcheanceGarantie());
        dto.setMainLevee(garantie.getMainLevee());
        dto.setDateMainLevee(garantie.getDateMainLevee());
        dto.setStatut(garantie.getStatut());
        dto.setDeviseContrat(garantie.getDeviseContrat());
        dto.setCodeProduit(garantie.getCodeProduit());
        logger.info("Mapped Garantie to DTO: {}", dto);
        return dto;
    }


    @GetMapping("/GetGarantieById/{id}")
    public ResponseEntity<GarantieNewDto> getGarantieById(@PathVariable Long id) {
        GarantieNewDto garantie = garantieService.getGarantieById(id);
        return garantie != null ? ResponseEntity.ok(garantie) : ResponseEntity.notFound().build();
    }


    @PostMapping("/createGarantie")

    public ResponseEntity<Garantie> createGarantie(@RequestBody GarantieNewDto garantieDto) {
        Garantie garantie = garantieService.createGarantie(garantieDto);
        return new ResponseEntity<>(garantie, HttpStatus.CREATED);
    }

    @PutMapping("/garantie-update/{id}")
    public ResponseEntity<GarantieNewDto> updateGarantie(@PathVariable Long id, @RequestBody GarantieNewDto garantieDto) {
        Garantie updatedGarantie = garantieService.updateGarantie(id, garantieDto);
        GarantieNewDto updatedGarantieDto = convertToDto(updatedGarantie);
        return ResponseEntity.ok(updatedGarantieDto);
    }

    private GarantieNewDto convertToDto(Garantie garantie) {
        GarantieNewDto garantieDto = new GarantieNewDto();
        garantieDto.setId(garantie.getIdGarantie());
        garantieDto.setDossier(garantie.getDossier().getIdDossier());
        garantieDto.setDateCreation(garantie.getDateCreation());
        garantieDto.setDateSignature(garantie.getDateSignature());
        garantieDto.setAgenceFinancement(garantie.getAgenceFinancement());
        garantieDto.setMontantGarantie(garantie.getMontantGarantie());
        garantieDto.setDeviseGarantie(garantie.getDeviseGarantie());
        garantieDto.setEcheanceGarantie(garantie.getEcheanceGarantie());
        garantieDto.setMainLevee(garantie.getMainLevee());
        garantieDto.setDateMainLevee(garantie.getDateMainLevee());
        garantieDto.setStatut(garantie.getStatut());
        return garantieDto;
    }

    @DeleteMapping("/garantie-delete/{id}")
    public ResponseEntity<Void> deleteGarantie(@PathVariable Long id) {
        boolean isRemoved = garantieService.deleteGarantie(id);
        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
