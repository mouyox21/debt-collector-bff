package com.mercureit.DebtCollectorBFF.services.pieceJointe;

import com.mercureit.DebtCollectorBFF.dto.PieceJointeDto;
import com.mercureit.DebtCollectorBFF.dto.PieceJointeProjection;
import com.mercureit.DebtCollectorBFF.entities.PieceJointe;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
import com.mercureit.DebtCollectorBFF.repositories.PieceJointeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PieceJointeServiceImp implements PieceJointeService {
    private static final Logger logger = LoggerFactory.getLogger(PieceJointeServiceImp.class);

    @Autowired
    PieceJointeRepository pieceJointeRepository;

    @Autowired
    IMapClassWithDto<PieceJointe, PieceJointeDto> PieceJointeMapping;

    @Override
    public Page<PieceJointeDto> getAllPieceJointes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
//        return pieceJointeRepository.findAll(pageable)
//                .map(pieceJointe -> PieceJointeMapping.convertToDto(pieceJointe, PieceJointeDto.class));
        Page<PieceJointeProjection> pieceJointes = pieceJointeRepository.findAllPieceJointe(pageable);
        return pieceJointes.map(pieceJointe -> {
                    PieceJointeDto dto = new PieceJointeDto();
                    dto.setIdFile(pieceJointe.getIdFile());
                    dto.setNomFile(pieceJointe.getNomFile());
                    dto.setTypeFile(pieceJointe.getTypeFile());
                    dto.setTailleFile(pieceJointe.getTailleFile());
                    dto.setDateCreation(pieceJointe.getDateCreation());
                    dto.setPath(pieceJointe.getPath());
                    dto.setPermissionAccess(pieceJointe.getPermissionAccess());
                    dto.setStatusFile(pieceJointe.getStatusFile());
                    dto.setTypepiecejoindre(pieceJointe.getTypepiecejoindre());
                    return dto;
                }
        );
    }



    @Override
    public PieceJointeDto getPieceJointeById(Long id) {
        return PieceJointeMapping.convertToDto(pieceJointeRepository.findById(id).get(), PieceJointeDto.class);
    }

    @Override
    public PieceJointeDto savePieceJointe(PieceJointeDto PieceJointeDto) {
        return PieceJointeMapping.convertToDto(pieceJointeRepository.save(PieceJointeMapping.convertToEntity(PieceJointeDto, PieceJointe.class)), PieceJointeDto.class);
    }

    @Override
    public PieceJointeDto updatePieceJointe(Long id, PieceJointeDto PieceJointeDto) {
        return PieceJointeMapping.convertToDto(pieceJointeRepository.save(PieceJointeMapping.convertToEntity(PieceJointeDto, PieceJointe.class)), PieceJointeDto.class);
    }

    @Override
    public void deletePieceJointe(Long id) {
        pieceJointeRepository.deleteById(id);
    }

    @Override
    public Page<PieceJointeDto> getPieceJointesByPersonne(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PieceJointeProjection> pieceJointePage = pieceJointeRepository.findByPersonneId(id, pageable);
        return pieceJointePage.map(pieceJointe -> {
                    PieceJointeDto dto = new PieceJointeDto();
                    dto.setIdFile(pieceJointe.getIdFile());
                    dto.setNomFile(pieceJointe.getNomFile());
                    dto.setTypeFile(pieceJointe.getTypeFile());
                    dto.setTailleFile(pieceJointe.getTailleFile());
                    dto.setDateCreation(pieceJointe.getDateCreation());
                    dto.setPath(pieceJointe.getPath());
                    dto.setPermissionAccess(pieceJointe.getPermissionAccess());
                    dto.setStatusFile(pieceJointe.getStatusFile());
                    dto.setTypepiecejoindre(pieceJointe.getTypepiecejoindre());
                    return dto;
                }
        );
    }

    @Override
    public Page<PieceJointeDto> getPieceJointesByDossier(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PieceJointeProjection> pieceJointePage = pieceJointeRepository.findByDossierId(id, pageable);
        return pieceJointePage.map(pieceJointe -> {
            PieceJointeDto dto = new PieceJointeDto();
            dto.setIdFile(pieceJointe.getIdFile());
            dto.setNomFile(pieceJointe.getNomFile());
            dto.setTypeFile(pieceJointe.getTypeFile());
            dto.setTailleFile(pieceJointe.getTailleFile());
            dto.setDateCreation(pieceJointe.getDateCreation());
            dto.setPath(pieceJointe.getPath());
            dto.setPermissionAccess(pieceJointe.getPermissionAccess());
            dto.setStatusFile(pieceJointe.getStatusFile());
            dto.setTypepiecejoindre(pieceJointe.getTypepiecejoindre());
            return dto;
        });
    }}


