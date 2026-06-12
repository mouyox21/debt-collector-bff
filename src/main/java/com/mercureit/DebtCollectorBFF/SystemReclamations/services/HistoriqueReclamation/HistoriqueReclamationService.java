package com.mercureit.DebtCollectorBFF.SystemReclamations.services.HistoriqueReclamation;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.HistoriqueReclamationDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HistoriqueReclamationService {
    HistoriqueReclamationDTO getHistoriqueReclamationById(Long id);
    HistoriqueReclamationDTO saveHistoriqueReclamation(HistoriqueReclamationDTO historiqueReclamationDTO);
    Page<HistoriqueReclamationDTO> listHistoriqueReclamations(int page, int size);
    HistoriqueReclamationDTO updateHistoriqueReclamation(Long id,HistoriqueReclamationDTO historiqueReclamationDTO);
    void deleteHistoriqueReclamation(Long id);
    List<HistoriqueReclamationDTO> listHistoriqueReclamationsByClaim(Long claimId);

}
