package com.mercureit.DebtCollectorBFF.SystemReclamations.services.Reclamation;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.ReclamationDTO;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.HistoriqueReclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimPriority;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimStatus;
import com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions.EntityNotFoundException;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.HistoriqueReclamationRepository;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.ReclamationRepository;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.Notification.NotificationServiceImpl;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
import com.mercureit.DebtCollectorBFF.services.pieceJointe.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    private final ReclamationRepository reclamationRepository;
    private final IMapClassWithDto<Reclamation, ReclamationDTO> reclamationMapper;
    private final HistoriqueReclamationRepository historiqueReclamationRepository;
    private final NotificationServiceImpl notificationService;
    private final MinioService minioService;


    @Autowired
    public ReclamationServiceImpl(ReclamationRepository reclamationRepository, IMapClassWithDto<Reclamation, ReclamationDTO> reclamationMapper, HistoriqueReclamationRepository historiqueReclamationRepository, NotificationServiceImpl notificationService, MinioService minioService) {
        this.reclamationRepository = reclamationRepository;
        this.reclamationMapper = reclamationMapper;
        this.historiqueReclamationRepository = historiqueReclamationRepository;
        this.notificationService = notificationService;
        this.minioService = minioService;
    }

    @Override
    public ReclamationDTO getReclamationById(Long id) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + id));
        return reclamationMapper.convertToDto(reclamation, ReclamationDTO.class);
    }
    @Override
    public List<ReclamationDTO> getReclamationsByEmployeId(String id){
        List<Reclamation> reclamations = reclamationRepository.findByCreatedByOrderByIdDesc(id);
        return reclamations.stream()
                .map(r -> reclamationMapper.convertToDto(r, ReclamationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReclamationDTO saveReclamation(ReclamationDTO reclamationDTO) {
        Reclamation reclamation = reclamationMapper.convertToEntity(reclamationDTO, Reclamation.class);
        reclamation.setCreatedDate(LocalDate.now());
        Reclamation savedReclamation = reclamationRepository.save(reclamation);

        HistoriqueReclamation historique = new HistoriqueReclamation(
                LocalDate.now(),
                "Création",
                "Réclamation créée",
                savedReclamation,
                savedReclamation.getCreatedBy()
        );
        historiqueReclamationRepository.save(historique);
        String notificationMessage = String.format("Une nouvelle réclamation a été créée %d, Priorité: %s , Categorie :  %s .", savedReclamation.getId(), savedReclamation.getPriority(),savedReclamation.getClaimCategory());
        String notifTitle="Création d'une Réclamation";

        notificationService.sendNotification(notifTitle,notificationMessage,savedReclamation.getCreatedBy());

        return reclamationMapper.convertToDto(savedReclamation, ReclamationDTO.class);
    }

    @Override
    public ReclamationDTO updateReclamation(Long id, ReclamationDTO updatedReclamationDTO) {
       // Reclamation updatedReclamation = reclamationMapper.convertToEntity(updatedReclamationDTO, Reclamation.class);
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + id));

        reclamation.setTitle(updatedReclamationDTO.getTitle());
        reclamation.setDescription(updatedReclamationDTO.getDescription());
        reclamation.setPriority(updatedReclamationDTO.getPriority());
        reclamation.setStatus(updatedReclamationDTO.getStatus());
        reclamation.setAssignedTo(updatedReclamationDTO.getAssignedTo());
        reclamation.setClaimCategory(updatedReclamationDTO.getClaimCategory());
        reclamation.setResolvedDate(updatedReclamationDTO.getResolvedDate());

        Reclamation savedReclamation = reclamationRepository.save(reclamation);

        // Ajouter une entrée dans l'historique pour la mise à jour
        HistoriqueReclamation historique = new HistoriqueReclamation(
                LocalDate.now(),
                "Mise à jour",
                "Réclamation mise à jour",
                reclamation,
                updatedReclamationDTO.getCreatedBy()
        );
        historiqueReclamationRepository.save(historique);

        String notificationMessage = String.format("La réclamation %d a été mise à jour.", savedReclamation.getId());
        String notifTitle="Mise a jour d'une Réclamation";

        notificationService.sendNotification(notifTitle,notificationMessage,savedReclamation.getCreatedBy());

        return reclamationMapper.convertToDto(savedReclamation, ReclamationDTO.class);
    }

    @Override
    public void deleteReclamation(Long id) {
        if (!reclamationRepository.existsById(id)) {
            throw new EntityNotFoundException("Reclamation not found with id " + id);
        }
        reclamationRepository.deleteById(id);
        //notificationService.sendNotification("La réclamation " +id + " a été supprimée.");

    }

    @Override
    public Page<ReclamationDTO> listReclamations(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Reclamation> reclamations = reclamationRepository.findAll(pageRequest);
        return reclamations.map(r -> reclamationMapper.convertToDto(r, ReclamationDTO.class));
    }

    @Override
    public ReclamationDTO assignReclamation(Long reclamationId, String userId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + reclamationId));
        reclamation.setAssignedTo(userId);
        Reclamation savedReclamation = reclamationRepository.save(reclamation);
        // Ajouter une entrée dans l'historique pour l'assignation
        HistoriqueReclamation historique = new HistoriqueReclamation(
                LocalDate.now(),
                "Assignation",
                "Réclamation assignée à l'employé " + userId,
                savedReclamation,
                savedReclamation.getCreatedBy()
        );
        historiqueReclamationRepository.save(historique);

        return reclamationMapper.convertToDto(savedReclamation, ReclamationDTO.class);
    }

    @Override
    public ReclamationDTO changeReclamationStatus(Long reclamationId, String status,String userId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + reclamationId));
        reclamation.setStatus(ClaimStatus.valueOf(status)); // Ensure the status is set properly
        // Vérifiez si le statut est RESOLVED et définissez la date de résolution
        if (ClaimStatus.valueOf(status) == ClaimStatus.RESOLVED) {
            reclamation.setResolvedDate(LocalDate.now()); // Assurez-vous que vous avez un champ dateResolution dans votre entité Reclamation
        }
        Reclamation savedReclamation = reclamationRepository.save(reclamation);
        // Ajouter une entrée dans l'historique pour le changement de status

        HistoriqueReclamation historique = new HistoriqueReclamation(
                LocalDate.now(),
                "Changement de statut",
                "Statut de la réclamation changé à " + status,
                savedReclamation,
                userId
        );
        historiqueReclamationRepository.save(historique);

        String notificationMessage = String.format("Le Statut de la réclamation %d changé à %s .", reclamationId,status);
        String notifTitle="Mise a jour d'une Réclamation";
        notificationService.sendNotification(notifTitle,notificationMessage,savedReclamation.getCreatedBy());
        return reclamationMapper.convertToDto(savedReclamation, ReclamationDTO.class);
    }
    @Override
    public ReclamationDTO changeReclamationPriority(Long reclamationId, String priority,String userId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + reclamationId));
        reclamation.setPriority(ClaimPriority.valueOf(priority)); // Ensure the status is set properly
        Reclamation savedReclamation = reclamationRepository.save(reclamation);
        // Ajouter une entrée dans l'historique pour le changement de priority

        HistoriqueReclamation historique = new HistoriqueReclamation(
                LocalDate.now(),
                "Changement de Priorité",
                "Priorité de la réclamation changé à " + priority,
                savedReclamation,
                userId
        );
        historiqueReclamationRepository.save(historique);

        String notificationMessage = String.format("La Priorité de la réclamation %d changé à %s .", reclamationId,priority);
        String notifTitle="Mise a jour d'une Réclamation";
        notificationService.sendNotification(notifTitle,notificationMessage,savedReclamation.getCreatedBy());
        return reclamationMapper.convertToDto(savedReclamation, ReclamationDTO.class);
    }

    @Override
    public List<ReclamationDTO> listReclamationsByUser(String userId) {
        List<Reclamation> reclamations = reclamationRepository.findByAssignedTo(userId);
        return reclamations.stream()
                .map(r -> reclamationMapper.convertToDto(r, ReclamationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReclamationDTO> listReclamationsByStatus(String status) {
        List<Reclamation> reclamations = reclamationRepository.findByStatus(ClaimStatus.valueOf(status));
        return reclamations.stream()
                .map(r -> reclamationMapper.convertToDto(r, ReclamationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReclamationDTO> listReclamationsByPriority(String priority) {
        List<Reclamation> reclamations = reclamationRepository.findByPriority(ClaimPriority.valueOf(priority));
        return reclamations.stream()
                .map(r -> reclamationMapper.convertToDto(r, ReclamationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReclamationDTO updateFileUrl(Long reclamationId, String fileUrl) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + reclamationId));
        reclamation.setFileUrl(fileUrl);
        reclamationRepository.save(reclamation);
        return reclamationMapper.convertToDto(reclamation,ReclamationDTO.class); // Méthode pour convertir Entity en DTO
    }

    /**
     * @param title
     * @param createdDate
     * @param createdBy
     * @param status
     * @param priority
     * @param pageable
     * @return
     */
    @Override
    public Page<ReclamationDTO> filterReclamations(Pageable pageable,
                                                   String title,
                                                   LocalDate createdDate,
                                                   String createdBy,
                                                   ClaimStatus status,
                                                   ClaimPriority priority) {
        Page<Reclamation> reclamations = reclamationRepository.filterReclamations(pageable, title, createdDate, createdBy, status, priority);
        return reclamations.map(r -> reclamationMapper.convertToDto(r, ReclamationDTO.class));
    }

    @Override
    public Map<String, Integer> getReclamationStats() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("total", reclamationRepository.findAll().size());
        stats.put("resolved", reclamationRepository.findByStatus(ClaimStatus.RESOLVED).size());
        stats.put("inProgress", reclamationRepository.findByStatus(ClaimStatus.IN_PROGRESS).size());
        stats.put("urgent", reclamationRepository.findByPriority(ClaimPriority.URGENT).size());
        return stats;
    }

    @Override
    public Map<String, Integer> getReclamationStatsByEmployeeId(String id) {
        Map<String, Integer> stats = new HashMap<>();
        List<Reclamation> reclamations = reclamationRepository.findByCreatedBy(id);

        stats.put("total", reclamations.size());
        stats.put("resolved", (int) reclamations.stream().filter(r -> r.getStatus() == ClaimStatus.RESOLVED).count());
        stats.put("inProgress", (int) reclamations.stream().filter(r -> r.getStatus() == ClaimStatus.IN_PROGRESS).count());
        stats.put("urgent", (int) reclamations.stream().filter(r -> r.getPriority() == ClaimPriority.URGENT).count());

        return stats;
    }



    @Override
    public ReclamationDTO saveReclamationWithFile(ReclamationDTO reclamationDTO, MultipartFile file) throws Exception {
        // Convert DTO to entity
        Reclamation reclamation = reclamationMapper.convertToEntity(reclamationDTO, Reclamation.class);
        reclamation.setCreatedDate(LocalDate.now());
        reclamation.setStatus(ClaimStatus.NEW);

        try {
            // Save the reclamation
            Reclamation savedReclamation = reclamationRepository.save(reclamation);

            // Handle file upload if file is present
            if (file != null && !file.isEmpty()) {
                try {
                    String fileUrl = minioService.uploadFile(file, "files", "reclamation-" + savedReclamation.getId() + "-file-" + file.getOriginalFilename());
                    savedReclamation.setFileUrl(fileUrl); // Assumes single file URL is handled
                    reclamationRepository.save(savedReclamation);
                } catch (Exception e) {
                    // Log the exception for debugging
                    // logger.error("Failed to upload file: {}", file.getOriginalFilename(), e);
                    throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
                }
            }

            // Create history record
            HistoriqueReclamation historique = new HistoriqueReclamation(
                    LocalDate.now(),
                    "Création",
                    "Réclamation créée avec fichier",
                    savedReclamation,
                    savedReclamation.getCreatedBy()
            );
            historiqueReclamationRepository.save(historique);

            // Send notification
            String notificationMessage = String.format("Une nouvelle réclamation a été créée %d, Priorité: %s .", savedReclamation.getId(), savedReclamation.getPriority());
            String notifTitle="Création d'une Réclamation";

            notificationService.sendNotification(notifTitle,notificationMessage,savedReclamation.getCreatedBy());

            // Convert entity to DTO and return
            return reclamationMapper.convertToDto(savedReclamation, ReclamationDTO.class);
        } catch (Exception e) {
            // Rollback if there is any failure in saving the reclamation or uploading the file
            // logger.error("Error saving reclamation with file", e);
            throw new Exception("Error saving reclamation with file: " + e.getMessage());
        }
    }
    @Override
    public String getReclamationFileUrl(Long reclamationId) throws Exception {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + reclamationId));

        if (reclamation.getFileUrl() == null) {
            throw new EntityNotFoundException("No file associated with this reclamation");
        }

        return minioService.getFile(reclamation.getFileUrl());
    }

}
