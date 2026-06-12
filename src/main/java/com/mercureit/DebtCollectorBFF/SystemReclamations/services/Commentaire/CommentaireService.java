package com.mercureit.DebtCollectorBFF.SystemReclamations.services.Commentaire;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.CommentaireDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentaireService {
    CommentaireDTO getCommentaireById(Long id);
    CommentaireDTO saveCommentaire(CommentaireDTO commentaireDTO,Long id);
    CommentaireDTO updateCommentaire(Long id,CommentaireDTO commentaireDTO);
    void deleteCommentaire(Long id);
    Page<CommentaireDTO> listCommentaires(int page, int size);
    List<CommentaireDTO> listCommentairesByClaim(long id);
    List<CommentaireDTO> listCommentairesByEmployeeId(String id);


}
