package com.mercureit.DebtCollectorBFF.services.personne.physique;

import com.mercureit.DebtCollectorBFF.dto.PphysiqueDTO;
import com.mercureit.DebtCollectorBFF.entities.PPhysique;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;
import com.mercureit.DebtCollectorBFF.mappers.MapClassWithDto;
import com.mercureit.DebtCollectorBFF.repositories.personne.PphysiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PphysiqueServiceImp implements PphysiqueService {
    @Autowired
    private PphysiqueRepository pphysiqueRepository;


    @Autowired
    private MapClassWithDto<PPhysique, PphysiqueDTO> pphysiqueMapping;

    @Override
    public PphysiqueDTO savePphysique(PphysiqueDTO pphysiqueDTO) throws PersonneNotFoundException {
        PPhysique pPhysique = pphysiqueMapping.convertToEntity(pphysiqueDTO, PPhysique.class);
        PPhysique savedPphysique = pphysiqueRepository.save(pPhysique);
        return pphysiqueMapping.convertToDto(savedPphysique, PphysiqueDTO.class);
    }
}

