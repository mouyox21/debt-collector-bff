package com.mercureit.DebtCollectorBFF.services.personne.physique;

import com.mercureit.DebtCollectorBFF.dto.PphysiqueDTO;
import com.mercureit.DebtCollectorBFF.entities.PMorale;
import com.mercureit.DebtCollectorBFF.entities.PPhysique;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;

public interface PphysiqueService {
    PphysiqueDTO savePphysique(PphysiqueDTO pphysiqueDTO) throws PersonneNotFoundException;

}
