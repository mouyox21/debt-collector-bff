package com.mercureit.DebtCollectorBFF.SystemReclamations.services.Reclamation;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.ReclamationDTO;

import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimPriority;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReclamationService {
    ReclamationDTO getReclamationById(Long id);
    List<ReclamationDTO>getReclamationsByEmployeId(String id);
    ReclamationDTO saveReclamation(ReclamationDTO reclamationDTO);
    ReclamationDTO updateReclamation(Long id, ReclamationDTO updatedReclamationDTO);
    void deleteReclamation(Long id);
    Page<ReclamationDTO> listReclamations(int page, int size);
    ReclamationDTO assignReclamation(Long reclamationId, String userId);
    ReclamationDTO changeReclamationStatus(Long reclamationId, String status,String userId);
    List<ReclamationDTO> listReclamationsByUser(String userId);
    List<ReclamationDTO> listReclamationsByStatus(String status);
    List<ReclamationDTO> listReclamationsByPriority(String priority);
    ReclamationDTO changeReclamationPriority(Long reclamationId, String priority,String userId);
    ReclamationDTO updateFileUrl(Long reclamationId, String fileUrl);
    Page<ReclamationDTO> filterReclamations(Pageable pageable,
                                         String title,
                                            LocalDate createdDate,
                                         String createdBy,
                                         ClaimStatus status,
                                         ClaimPriority priority);
     Map<String, Integer> getReclamationStats() ;
    ReclamationDTO saveReclamationWithFile(ReclamationDTO reclamationDTO, MultipartFile file) throws Exception;
    String getReclamationFileUrl(Long reclamationId) throws Exception;
    Map<String, Integer> getReclamationStatsByEmployeeId(String id) ;

}
