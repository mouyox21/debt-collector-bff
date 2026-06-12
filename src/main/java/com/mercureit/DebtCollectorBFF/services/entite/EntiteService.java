package com.mercureit.DebtCollectorBFF.services.entite;

import com.mercureit.DebtCollectorBFF.entities.Entite;
import com.mercureit.DebtCollectorBFF.exception.EntiteNotFoundException;
import com.mercureit.DebtCollectorBFF.projections.EntiteProjection;

import java.util.List;

public interface EntiteService {
    List<EntiteProjection> getAllEntites();
    Entite getEntiteById(Long id) throws EntiteNotFoundException;
}
