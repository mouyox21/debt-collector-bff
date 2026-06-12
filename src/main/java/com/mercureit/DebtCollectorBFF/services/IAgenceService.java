package com.mercureit.DebtCollectorBFF.services;

import com.mercureit.DebtCollectorBFF.dto.AgenceDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.projections.AgenceProjection;

import java.util.List;

public interface IAgenceService {


    List<AgenceDto> getAgences();

    AgenceDto findAgenceById(Long id);

    List<AgenceProjection> getAllAgences();
}
