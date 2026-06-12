package com.mercureit.DebtCollectorBFF.SystemReclamations.controllers;

import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.ReclamationDTO;
import com.mercureit.DebtCollectorBFF.SystemReclamations.entities.Reclamation;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimPriority;
import com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations.ClaimStatus;
import com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions.EntityNotFoundException;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.Reclamation.ReclamationService;
import com.mercureit.DebtCollectorBFF.services.pieceJointe.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reclamations")
@CrossOrigin
public class ReclamationController {

    private final ReclamationService reclamationService;
    private final MinioService minioService;

    @Autowired
    public ReclamationController(ReclamationService reclamationService, MinioService minioService) {
        this.reclamationService = reclamationService;
        this.minioService = minioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReclamationDTO> getReclamationById(@PathVariable("id") Long id) {
        ReclamationDTO reclamationDTO = reclamationService.getReclamationById(id);
        return ResponseEntity.ok(reclamationDTO);
    }
    @GetMapping("/user")
    public ResponseEntity<List<ReclamationDTO>> getReclamationsByEmployeId(@RequestParam String id){
        List<ReclamationDTO> reclamations = reclamationService.getReclamationsByEmployeId(id);
        return ResponseEntity.ok(reclamations);
    }

    @PostMapping
    public ResponseEntity<ReclamationDTO> saveReclamation(@RequestBody ReclamationDTO reclamationDTO) {
        ReclamationDTO savedReclamation = reclamationService.saveReclamation(reclamationDTO);
        return new ResponseEntity<>(savedReclamation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReclamationDTO> updateReclamation(@PathVariable("id") Long id, @RequestBody ReclamationDTO updatedReclamationDTO) {
        ReclamationDTO updated = reclamationService.updateReclamation(id, updatedReclamationDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable("id") Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/filter")
    public Page<ReclamationDTO> filterReclamations(
            Pageable pageable,
            @RequestParam(required = false) String title,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdDate,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority
    ) {
        // Convert status and priority to enums
        ClaimStatus claimStatus = (status != null) ? ClaimStatus.valueOf(status) : null;
        ClaimPriority claimPriority = (priority != null) ? ClaimPriority.valueOf(priority) : null;

        return reclamationService.filterReclamations(pageable, title, createdDate, createdBy, claimStatus, claimPriority);
    }


    @GetMapping
    public ResponseEntity<Page<ReclamationDTO>> listReclamations(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Page<ReclamationDTO> reclamations = reclamationService.listReclamations(page, size);
        return ResponseEntity.ok(reclamations);
    }

    @PostMapping("/{reclamationId}/assign/{userId}")
    public ResponseEntity<ReclamationDTO> assignReclamation(@PathVariable("reclamationId") Long reclamationId,
                                                            @PathVariable("userId") String userId) {
        ReclamationDTO reassignedReclamation = reclamationService.assignReclamation(reclamationId, userId);
        return ResponseEntity.ok(reassignedReclamation);
    }

    @PostMapping("/{reclamationId}/status/{status}")
    public ResponseEntity<ReclamationDTO> changeReclamationStatus(@PathVariable("reclamationId") Long reclamationId,
                                                                  @PathVariable("status") String status,
                                                                  @RequestParam String userId) {
        ReclamationDTO updatedReclamation = reclamationService.changeReclamationStatus(reclamationId, status,userId);
        return ResponseEntity.ok(updatedReclamation);
    }
    @PostMapping("/{reclamationId}/priority/{priority}")
    public ResponseEntity<ReclamationDTO> changeReclamationPriority(@PathVariable("reclamationId") Long reclamationId,
                                                                  @PathVariable("priority") String priority,
                                                                    @RequestParam String userId) {
        ReclamationDTO updatedReclamation = reclamationService.changeReclamationPriority(reclamationId, priority,userId);
        return ResponseEntity.ok(updatedReclamation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReclamationDTO>> listReclamationsByUser(@PathVariable("userId") String userId) {
        List<ReclamationDTO> reclamations = reclamationService.listReclamationsByUser(userId);
        return ResponseEntity.ok(reclamations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReclamationDTO>> listReclamationsByStatus(@PathVariable("status") String status) {
        List<ReclamationDTO> reclamations = reclamationService.listReclamationsByStatus(status);
        return ResponseEntity.ok(reclamations);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<ReclamationDTO>> listReclamationsByPriority(@PathVariable("priority") String priority) {
        List<ReclamationDTO> reclamations = reclamationService.listReclamationsByPriority(priority);
        return ResponseEntity.ok(reclamations);
    }
    @PostMapping("/{id}/upload-file")
    public ResponseEntity<String> uploadFile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            String objectName = minioService.uploadFile(file, "files", "reclamation-" + id + "-file");
            ReclamationDTO updatedReclamationDTO = reclamationService.updateFileUrl(id, objectName);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Integer>> getReclamationStats() {
       return ResponseEntity.ok(reclamationService.getReclamationStats());
    }
    @GetMapping("/stats/{id}")
    public ResponseEntity<Map<String, Integer>> getReclamationStatsByEmployeeId(@PathVariable("id") String id) {
        return ResponseEntity.ok(reclamationService.getReclamationStatsByEmployeeId(id));
    }

    @PostMapping("/create-with-file")
    public ResponseEntity<ReclamationDTO> saveReclamationWithFile(
            @RequestPart("reclamation")ReclamationDTO reclamationDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            ReclamationDTO savedReclamation = reclamationService.saveReclamationWithFile(reclamationDTO, file);
            return new ResponseEntity<>(savedReclamation, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/{id}/file")
    public ResponseEntity<String> getReclamationFileUrl(@PathVariable Long id) {
        try {
            String fileUrl = reclamationService.getReclamationFileUrl(id);
            return ResponseEntity.ok(fileUrl);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving file URL");
        }
    }


}
