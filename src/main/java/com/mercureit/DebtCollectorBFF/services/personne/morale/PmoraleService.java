package com.mercureit.DebtCollectorBFF.services.personne.morale;

import com.mercureit.DebtCollectorBFF.dto.PmoraleDTO;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;

public interface PmoraleService {
    PmoraleDTO savePmorale(PmoraleDTO pmoraleDTO) throws PersonneNotFoundException;
}
