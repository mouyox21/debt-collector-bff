package com.mercureit.DebtCollectorBFF.SystemReclamations.controllers;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.CommentaireDTO;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.Commentaire.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
@CrossOrigin
public class CommentaireController {
    private final CommentaireService commentaireService;
    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommentaireDTO> getCommentaireById(@PathVariable("id") Long id) {
        CommentaireDTO commentaireDTO = commentaireService.getCommentaireById(id);
        return ResponseEntity.ok(commentaireDTO);
    }
    @GetMapping
    public ResponseEntity<Page<CommentaireDTO>> getAllCommentaires(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Page<CommentaireDTO> commentaires=commentaireService.listCommentaires(page,size);
        return ResponseEntity.ok(commentaires);
    }
    @PostMapping("/{id}")
    public ResponseEntity<CommentaireDTO> saveCommentaire(@RequestBody CommentaireDTO commentaireDTO,
                                                          @PathVariable("id") Long id) {
        CommentaireDTO commentaire=commentaireService.saveCommentaire(commentaireDTO,id);
        return new ResponseEntity<>(commentaire, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CommentaireDTO> updateCommentaire(@PathVariable("id") Long id,@RequestBody CommentaireDTO commentaireDTO) {
        CommentaireDTO commentaire=commentaireService.updateCommentaire(id, commentaireDTO);
        return ResponseEntity.ok(commentaire);
    }
    @GetMapping ("/employe/{id}")
    public ResponseEntity<List<CommentaireDTO>> listCommentairesByEmployeId(@PathVariable("id") String id) {
        List<CommentaireDTO> commentaires=commentaireService.listCommentairesByEmployeeId(id);
        return ResponseEntity.ok(commentaires);
    }
    @GetMapping("/claim/{id}")
    public ResponseEntity<List<CommentaireDTO>> listCommentairesByClaimId(@PathVariable("id") Long id) {
        List<CommentaireDTO> commentaires=commentaireService.listCommentairesByClaim(id);
        return ResponseEntity.ok(commentaires);
    }
    @DeleteMapping("id")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable("id") Long id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.noContent().build();
    }
}
