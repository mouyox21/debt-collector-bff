package com.mercureit.DebtCollectorBFF.services.Garantie;

import com.mercureit.DebtCollectorBFF.dto.GarantieDto;
import com.mercureit.DebtCollectorBFF.dto.GarantieNewDto;
import com.mercureit.DebtCollectorBFF.entities.Dossier;
import com.mercureit.DebtCollectorBFF.entities.Garantie;
import com.mercureit.DebtCollectorBFF.repositories.DossierRepository;
import com.mercureit.DebtCollectorBFF.repositories.GarantieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GarantieService implements IGarantieService {

    @Autowired
    private GarantieRepository garantieRepository;

    private static final Logger logger = LoggerFactory.getLogger(IGarantieService.class);


    @Autowired
    private DossierRepository dossierRepository;



    public GarantieService(GarantieRepository garantieRepository) {
        this.garantieRepository = garantieRepository;
        this.dossierRepository= dossierRepository;
    }

    @Override
    public Page<Garantie> getAllGaranties(int page, int size) {
        return garantieRepository.findAll(PageRequest.of(page, size));
    }

    @Override

    public GarantieNewDto getGarantieById(Long id) {
        return garantieRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    private GarantieNewDto convertToDto(Garantie garantie) {
        GarantieNewDto garantieDto = new GarantieNewDto();
        garantieDto.setId(garantie.getIdGarantie());
        garantieDto.setDossier(garantie.getDossier().getIdDossier());
        garantieDto.setStatut(garantie.getStatut());
        garantieDto.setAgenceFinancement(garantie.getAgenceFinancement());
//        garantieDto.setContactId(garantie.getContactId());
        garantieDto.setDateCreation(garantie.getDateCreation());
        garantieDto.setDateSignature(garantie.getDateSignature());
        garantieDto.setMontantGarantie(garantie.getMontantGarantie());
        garantieDto.setDeviseGarantie(garantie.getDeviseGarantie());
        garantieDto.setEcheanceGarantie(garantie.getEcheanceGarantie());
        garantieDto.setMainLevee(garantie.getMainLevee());
        garantieDto.setDateMainLevee(garantie.getDateMainLevee());
        garantieDto.setStatut(garantie.getStatut());
        garantieDto.setDeviseContrat(garantie.getDeviseContrat());
        garantieDto.setCodeProduit(garantie.getCodeProduit());

        return garantieDto;
    }


    @Override
    public Garantie createGarantie(GarantieNewDto garantieDto) {
        Dossier dossier = dossierRepository.findById(garantieDto.getDossier())
                .orElseThrow(() -> new RuntimeException("Dossier not found"));

        Garantie garantie = new Garantie();

        garantie.setDossier(dossier);

        garantie.setAgenceFinancement(garantieDto.getAgenceFinancement());
        garantie.setDateCreation(garantieDto.getDateCreation());
        garantie.setDateSignature(garantieDto.getDateSignature());
        garantie.setMontantGarantie(garantieDto.getMontantGarantie());
        garantie.setDeviseGarantie(garantieDto.getDeviseGarantie());
        garantie.setEcheanceGarantie(garantieDto.getEcheanceGarantie());
        garantie.setMainLevee(garantieDto.getMainLevee());
        garantie.setDateMainLevee(garantieDto.getDateMainLevee());
        garantie.setStatut(garantieDto.getStatut());
        garantie.setDeviseContrat(garantieDto.getDeviseContrat());
        garantie.setCodeProduit(garantieDto.getCodeProduit());
        garantie.setNumeroGarantie(garantieDto.getNumeroGarantie());
//        garantie.setContactId(garantieDto.getContactId());
        garantie.setDateFinReelle(garantieDto.getDateFinReelle());

        return garantieRepository.save(garantie);
    }

    @Override
    public Garantie updateGarantie(Long id, GarantieNewDto garantieDto) {
        if (garantieDto.getDossier() != null && !dossierRepository.existsById(garantieDto.getDossier())) {
            throw new RuntimeException("Dossier not found");
        }

        return garantieRepository.findById(id)
                .map(existingGarantie -> {
                    existingGarantie.setDateCreation(garantieDto.getDateCreation());
                    existingGarantie.setDateSignature(garantieDto.getDateSignature());
                    existingGarantie.setAgenceFinancement(garantieDto.getAgenceFinancement());
                    existingGarantie.setMontantGarantie(garantieDto.getMontantGarantie());
                    existingGarantie.setDeviseGarantie(garantieDto.getDeviseGarantie());
                    existingGarantie.setEcheanceGarantie(garantieDto.getEcheanceGarantie());
                    existingGarantie.setMainLevee(garantieDto.getMainLevee());
                    existingGarantie.setDateMainLevee(garantieDto.getDateMainLevee());
                    existingGarantie.setStatut(garantieDto.getStatut());
                    existingGarantie.setDeviseContrat(garantieDto.getDeviseContrat());
                    existingGarantie.setCodeProduit(garantieDto.getCodeProduit());
                    existingGarantie.setNumeroGarantie(garantieDto.getNumeroGarantie());
//                    existingGarantie.setContactId(garantieDto.getContactId());
                    existingGarantie.setDateFinReelle(garantieDto.getDateFinReelle());

                    if (garantieDto.getDossier() != null) {
                        Dossier dossier = dossierRepository.findById(garantieDto.getDossier())
                                .orElseThrow(() -> new RuntimeException("Dossier not found"));
                        existingGarantie.setDossier(dossier);
                    }

                    return garantieRepository.save(existingGarantie);
                })
                .orElseThrow(() -> new RuntimeException("Garantie not found"));

    }

    @Override
    public boolean deleteGarantie(Long id) {
//        garantieRepository.deleteById(id);
//        return false;
//    }
        if (garantieRepository.existsById(id)) {
            garantieRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
