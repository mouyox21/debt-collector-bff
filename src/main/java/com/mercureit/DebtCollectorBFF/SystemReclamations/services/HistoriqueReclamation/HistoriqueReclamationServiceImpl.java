package com.mercureit.DebtCollectorBFF.SystemReclamations.services.HistoriqueReclamation;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.HistoriqueReclamationDTO;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Commentaire;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.HistoriqueReclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions.EntityNotFoundException;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.HistoriqueReclamationRepository;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.ReclamationRepository;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoriqueReclamationServiceImpl implements HistoriqueReclamationService {

    @Autowired
    private HistoriqueReclamationRepository historiqueReclamationRepository;

    @Autowired
    private IMapClassWithDto<HistoriqueReclamation, HistoriqueReclamationDTO> mapper;
    @Autowired
    private ReclamationRepository reclamationRepository ;
    /**
     * @param id
     * @return
     */
    @Override
    public HistoriqueReclamationDTO getHistoriqueReclamationById(Long id) {
        HistoriqueReclamation historiqueReclamation = historiqueReclamationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HistoriqueReclamation not found with id " + id));
        return mapper.convertToDto(historiqueReclamation, HistoriqueReclamationDTO.class);
    }
    /**
     * @param historiqueReclamationDTO
     * @return
     */
    @Override
    public HistoriqueReclamationDTO saveHistoriqueReclamation(HistoriqueReclamationDTO historiqueReclamationDTO) {
        Reclamation claim = reclamationRepository.findById(historiqueReclamationDTO.getClaimId())
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + historiqueReclamationDTO.getClaimId()));

        HistoriqueReclamation historiqueReclamation = mapper.convertToEntity(historiqueReclamationDTO, HistoriqueReclamation.class);
        historiqueReclamation.setClaim(claim);

        historiqueReclamation = historiqueReclamationRepository.save(historiqueReclamation);
        return mapper.convertToDto(historiqueReclamation, HistoriqueReclamationDTO.class);
    }

    /**
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<HistoriqueReclamationDTO> listHistoriqueReclamations(int page, int size) {
        Page<HistoriqueReclamation> historiqueReclamations = historiqueReclamationRepository.findAll(PageRequest.of(page, size));
        return mapper.mapEntityPageIntoDtoPage(historiqueReclamations, HistoriqueReclamationDTO.class);
    }

    /**
     * @param id
     * @param historiqueReclamationDTO
     * @return
     */

    @Transactional
    @Override
    public HistoriqueReclamationDTO updateHistoriqueReclamation(Long id, HistoriqueReclamationDTO historiqueReclamationDTO) {
        HistoriqueReclamation historiqueReclamation = historiqueReclamationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HistoriqueReclamation not found with id " + id));

        Reclamation claim = reclamationRepository.findById(historiqueReclamationDTO.getClaimId())
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + historiqueReclamationDTO.getClaimId()));

        // Directly update the fields of the existing entity with values from the DTO
        historiqueReclamation.setAction(historiqueReclamationDTO.getAction());
        historiqueReclamation.setClaim(claim);
        historiqueReclamation.setDetails(historiqueReclamationDTO.getDetails());
        historiqueReclamation.setActionDate(historiqueReclamationDTO.getActionDate());
        historiqueReclamation.setEmployeeId(historiqueReclamationDTO.getEmployeeId());

        // Save the updated entity
        historiqueReclamation = historiqueReclamationRepository.save(historiqueReclamation);

        // Convert back to DTO
        return mapper.convertToDto(historiqueReclamation, HistoriqueReclamationDTO.class);
    }

    /**
     * @param id
     */
    @Override
    public void deleteHistoriqueReclamation(Long id) {
        if (!historiqueReclamationRepository.existsById(id)) {
            throw new EntityNotFoundException("HistoriqueReclamation not found with id " + id);
        }
        historiqueReclamationRepository.deleteById(id);
    }

    /**
     * @param claimId
     * @return
     */
    @Override
    public List<HistoriqueReclamationDTO> listHistoriqueReclamationsByClaim(Long claimId) {
        Reclamation claim = reclamationRepository.findById(claimId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + claimId));

        List<HistoriqueReclamation> historiqueReclamations=historiqueReclamationRepository.findByClaim(claim);
        return historiqueReclamations.stream()
                .map(r->mapper.convertToDto(r,HistoriqueReclamationDTO.class))
                .collect(Collectors.toList());


    }
}
