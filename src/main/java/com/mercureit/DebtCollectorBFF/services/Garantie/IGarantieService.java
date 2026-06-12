package com.mercureit.DebtCollectorBFF.services.Garantie;

import com.mercureit.DebtCollectorBFF.dto.GarantieDto;
import com.mercureit.DebtCollectorBFF.dto.GarantieNewDto;
import com.mercureit.DebtCollectorBFF.entities.Garantie;
import org.springframework.data.domain.Page;

public interface IGarantieService {
    Page<Garantie> getAllGaranties(int page, int size);

    GarantieNewDto getGarantieById(Long id);

    Garantie createGarantie(GarantieNewDto garantieDto);

    Garantie updateGarantie(Long id, GarantieNewDto garantieDto);

    boolean deleteGarantie(Long id);
}
