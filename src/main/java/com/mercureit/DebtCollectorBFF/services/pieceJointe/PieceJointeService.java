package com.mercureit.DebtCollectorBFF.services.pieceJointe;

import com.mercureit.DebtCollectorBFF.dto.PieceJointeDto;
import com.mercureit.DebtCollectorBFF.entities.PieceJointe;
import com.mercureit.DebtCollectorBFF.exception.PieceJointeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PieceJointeService {
    Page<PieceJointeDto> getAllPieceJointes(int page, int size);

    Page<PieceJointeDto> getPieceJointesByPersonne(Long id, int page, int size) throws PieceJointeNotFoundException;

    Page<PieceJointeDto> getPieceJointesByDossier(Long id, int page, int size) throws PieceJointeNotFoundException;

    PieceJointeDto getPieceJointeById(Long id) throws PieceJointeNotFoundException;

    PieceJointeDto savePieceJointe(PieceJointeDto pieceJointeDTO);

    PieceJointeDto updatePieceJointe(Long id, PieceJointeDto pieceJointeDTO) throws PieceJointeNotFoundException;
    void deletePieceJointe(Long id) throws PieceJointeNotFoundException;

}
