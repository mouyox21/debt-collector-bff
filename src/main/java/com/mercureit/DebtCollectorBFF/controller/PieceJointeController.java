package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.dto.PieceJointeDto;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.exception.PieceJointeNotFoundException;
import com.mercureit.DebtCollectorBFF.services.dossier.DossierService;
import com.mercureit.DebtCollectorBFF.services.personne.PersonneService;
import com.mercureit.DebtCollectorBFF.services.pieceJointe.MinioService;
import com.mercureit.DebtCollectorBFF.services.pieceJointe.PieceJointeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class PieceJointeController {
    private static final Logger logger = LoggerFactory.getLogger(PieceJointeController.class);

    @Autowired
    PieceJointeService pieceJointeService;

    @Autowired
    PersonneService personneService;

    @Autowired
    DossierService dossierService;

    @Autowired
    private MinioService minioService;

    @GetMapping("/pieceJointes-list")
    public ResponseEntity<Page<PieceJointeDto>> getAllPieceJointes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PieceJointeDto> pieceJointePage = pieceJointeService.getAllPieceJointes(page, size);
        return ResponseEntity.ok(pieceJointePage);
    }

    @GetMapping("/pieceJointes-personne/{id}")
    public ResponseEntity<Page<PieceJointeDto>> getPieceJointesByPersonne(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long id) throws PieceJointeNotFoundException {
        Page<PieceJointeDto> pieceJointePage = pieceJointeService.getPieceJointesByPersonne( id, page,  size);
        return ResponseEntity.ok(pieceJointePage);
    }
    @GetMapping("/pieceJointes-dossier/{id}")
    public ResponseEntity<Page<PieceJointeDto>> getPieceJointesByDossier(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long id) throws PieceJointeNotFoundException {
        Page<PieceJointeDto> pieceJointePage = pieceJointeService.getPieceJointesByDossier( id, page,  size);
        return ResponseEntity.ok(pieceJointePage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PieceJointeDto> getPieceJointe(@PathVariable Long id) throws PieceJointeNotFoundException {
        return ResponseEntity.ok(pieceJointeService.getPieceJointeById(id));
    }


    @PostMapping(value = "/personne/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PieceJointeDto> uploadFile(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("type") String type,
                                                     @PathVariable Long id
                                                     ) {
        try {

            Personne p = personneService.getPersonneById(id);
            if (p == null) {
                return ResponseEntity.notFound().build();
            }

            String folderName = "PERSONNES/" + p.getType() + "/PERSON-" + p.getId();
            String fileName = "";

            if ("PHYSIQUE".equalsIgnoreCase(p.getType().toString())) {
                switch (type) {
                    case "CIN":
                        fileName = type + p.getNumIdentif();
                        folderName += "/pieces-jointes/CIN";
                        break;
                    case "PASSPORT":
                        fileName = type + p.getNumIdentif();
                        folderName += "/pieces-jointes/PASSPORT";
                        break;
                    case "CARTE_JOUR":
                        fileName = type + p.getNumIdentif();
                        folderName += "/pieces-jointes/CARTE_JOUR";
                        break;
                    default:
                        fileName = file.getOriginalFilename();
                        folderName += "/Autre-documents";
                        break;
                }
            } else if ("MORALE".equalsIgnoreCase(p.getType().toString())) {
                switch (type) {
                    case "ICE":
                        fileName = type + p.getNumIdentif();
                        folderName += "/pieces-jointes/ICE";
                        break;
                    case "RC":
                        fileName = type + p.getNumIdentif();
                        folderName += "/pieces-jointes/RC";
                        break;
                    default:
                        fileName = file.getOriginalFilename();
                        folderName += "/autres-documents";
                        break;
                }
            } else {
                fileName = file.getOriginalFilename();
                folderName += "/autres-documents";
            }


            String objectName = minioService.uploadFile(file, folderName, fileName+".pdf");

            PieceJointeDto dto = new PieceJointeDto();
            dto.setNomFile(fileName);
            dto.setPath(objectName);
            dto.setTailleFile(file.getSize());
            dto.setTypeFile(file.getContentType());
            dto.setDateCreation(LocalDate.now());
            System.out.println(dto.getDateCreation());
            dto.setStatusFile("Uploaded");
            dto.setPermissionAccess("Public");
//            dto.setDossier(null);
//            dto.setPersonne(p);
//            dto.setTypepiecejoindre(type);

            pieceJointeService.savePieceJointe(dto);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("File upload error", e);
            return ResponseEntity.status(500).body(null);
        }
    }



    @GetMapping("/view/{id}")
    public ResponseEntity<String> downloadFile(@PathVariable Long id) {
        try {
            PieceJointeDto dto = pieceJointeService.getPieceJointeById(id);
            String fileName = dto.getPath();
             String url = minioService.getFile(fileName);
            System.out.println(url);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            logger.error("File download error", e);
            return ResponseEntity.status(500).body("Error generating presigned URL");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            PieceJointeDto dto = pieceJointeService.getPieceJointeById(id);
            String fileName = dto.getPath();
            minioService.deleteFile(fileName);
            pieceJointeService.deletePieceJointe(id);
            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            logger.error("File delete error", e);
            return ResponseEntity.status(500).body("Error deleting file");
        }
    }


    @PostMapping(value = "/dossier/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PieceJointeDto> uploadDossier(@RequestParam("file") MultipartFile file,
                                                        @RequestParam("type") String type,
                                                        @PathVariable Long id
                                                        ) throws PieceJointeNotFoundException {
        try {


            // Check if dossier exists
            DossierDto d = dossierService.findDossierById(id);


            if (d == null) {
                return ResponseEntity.notFound().build();
            }

            String folderName = "DOSSIER/DOSSIER-" + d.getIdDossier() ;

            String fileName = type + d.getPersonne().getNumIdentif() + "-" + LocalDate.now().toString();
            String objectName = minioService.uploadFile(file, folderName, fileName);

            PieceJointeDto dto = new PieceJointeDto();
            dto.setNomFile(fileName);
            dto.setPath(objectName);
            dto.setTailleFile(file.getSize());
            dto.setTypeFile(file.getContentType());
            dto.setDateCreation(LocalDate.now());
            dto.setStatusFile("Uploaded");
            dto.setPermissionAccess("Public");
            dto.setDossier(d);
            dto.setPersonne(d.getPersonne());


            pieceJointeService.savePieceJointe(dto);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("File upload error", e);
            return ResponseEntity.status(500).body(null);
        }
    }

}
