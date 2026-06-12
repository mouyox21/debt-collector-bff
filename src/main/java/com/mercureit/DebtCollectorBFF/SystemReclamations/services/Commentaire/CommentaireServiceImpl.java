package com.mercureit.DebtCollectorBFF.SystemReclamations.services.Commentaire;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.CommentaireDTO;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Commentaire;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions.DuplicateEntityException;
import com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions.EntityNotFoundException;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.CommentaireRepository;
import com.mercureit.DebtCollectorBFF.SystemReclamations.repositories.ReclamationRepository;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.Notification.NotificationServiceImpl;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaireServiceImpl implements CommentaireService{


    private CommentaireRepository commentaireRepository;
    private IMapClassWithDto<Commentaire, CommentaireDTO> mapper;
    private ReclamationRepository reclamationRepository;
    private final NotificationServiceImpl notificationService;

    @Autowired
    public CommentaireServiceImpl(CommentaireRepository repository, IMapClassWithDto<Commentaire, CommentaireDTO> mapper, ReclamationRepository reclamationRepository, NotificationServiceImpl notificationService)
    {
        this.commentaireRepository = repository;
        this.mapper = mapper;
        this.reclamationRepository = reclamationRepository;
        this.notificationService = notificationService;
    }
    /**
     * @param id
     * @return
     */
    @Override
    public CommentaireDTO getCommentaireById(Long id) {
        Commentaire commentaire=commentaireRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Commentaire not found"));
        return mapper.convertToDto(commentaire,CommentaireDTO.class);
    }

    /**
     * @param commentaireDTO
     * @return
     */
    @Override
    public CommentaireDTO saveCommentaire(CommentaireDTO commentaireDTO,Long id) {

        Commentaire commentaire=mapper.convertToEntity(commentaireDTO,Commentaire.class);
        Reclamation claim = reclamationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + commentaireDTO.getClaimId()));
        if (commentaireRepository.existsById(commentaire.getId())){
            throw new DuplicateEntityException("Commentaire with id "+commentaire.getId()+" already exists");
        }
        commentaire.setClaim(claim);
        commentaire.setTimestamp(LocalDate.now());
        commentaireRepository.save(commentaire);
        String notificationMessage = String.format("un commentaire a été ajouté a la réclamation %d, Priorité: %s .", id, claim.getPriority());
        String notifTitle="Add Commentaire";

        notificationService.sendNotification(notifTitle,notificationMessage,claim.getCreatedBy());



        return mapper.convertToDto(commentaire,CommentaireDTO.class);

    }

    /**
     * @param id
     * @param commentaireDTO
     * @return
     */
    @Transactional
    @Override
    public CommentaireDTO updateCommentaire(Long id, CommentaireDTO commentaireDTO) {
       Commentaire commentaire=commentaireRepository.findById(id)
               .orElseThrow(()->new EntityNotFoundException("Commentaire not found"));
        Reclamation claim = reclamationRepository.findById(commentaireDTO.getClaimId())
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + commentaireDTO.getClaimId()));

       commentaire.setContent(commentaireDTO.getContent());
       commentaire.setClaim(claim);
       commentaire.setTimestamp(commentaireDTO.getTimestamp());
       commentaire.setEmployeeId(commentaireDTO.getEmployeeId());
       commentaireRepository.save(commentaire);
        String notificationMessage = String.format("Le commentaire %d de la réclamation %s, Priorité: %s a été mis à jour.",
                commentaire.getId(), id, claim.getPriority());
        String notifTitle="Update Commentaire";

        notificationService.sendNotification(notifTitle,notificationMessage,claim.getCreatedBy());
        return mapper.convertToDto(commentaire,CommentaireDTO.class);
    }

    /**
     * @param id
     */
    @Override
    public void deleteCommentaire(Long id) {
        if (!commentaireRepository.existsById(id)) {
            throw new EntityNotFoundException("Commentaire not found with id " + id);
        }
        commentaireRepository.deleteById(id);
        //notificationService.sendNotification("le commentaire "+ id+ " a été supprimé.");

    }

    /**
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<CommentaireDTO> listCommentaires(int page, int size) {
       Page<Commentaire>commentaires=commentaireRepository.findAll(PageRequest.of(page,size));

       return commentaires.map(r->mapper.convertToDto(r,CommentaireDTO.class));


    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<CommentaireDTO> listCommentairesByClaim(long id) {
        Reclamation claim = reclamationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + id));
        List<Commentaire> commentaires = commentaireRepository.findByClaim(claim);

        return commentaires.stream()
                .map(r -> mapper.convertToDto(r, CommentaireDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<CommentaireDTO> listCommentairesByEmployeeId(String id) {
        List<Commentaire>commentaires=commentaireRepository.findByEmployeeId(id);
        return commentaires.stream()
                .map(r->mapper.convertToDto(r,CommentaireDTO.class))
                .collect(Collectors.toList());

    }
}
