package com.mercureit.DebtCollectorBFF.services;

import java.util.List;

import com.mercureit.DebtCollectorBFF.dto.AgenceDto;
import com.mercureit.DebtCollectorBFF.entities.Agence;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
import com.mercureit.DebtCollectorBFF.projections.AgenceProjection;
import com.mercureit.DebtCollectorBFF.repositories.AgenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenceService implements IAgenceService {

    private static final Logger log = LoggerFactory.getLogger(AgenceService.class);

    @Autowired
    AgenceRepository agenceRepository;

    @Autowired
    IMapClassWithDto<Agence, AgenceDto> agenceMapping;

    @Override
    public List<AgenceDto> getAgences() {

        List<Agence> agences = agenceRepository.findAll();

        return agenceMapping.convertListToListDto(agences, AgenceDto.class);

    }

    @Override
    public AgenceDto findAgenceById(Long id) {
        return agenceMapping.convertToDto( agenceRepository.findById(id).get() , AgenceDto.class );
    }


    @Override
    public List<AgenceProjection> getAllAgences() {
        return agenceRepository.findAllAgenceIdAndNom();
    }


}
