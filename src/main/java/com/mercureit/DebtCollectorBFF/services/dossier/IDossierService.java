package com.mercureit.DebtCollectorBFF.services.dossier;

import com.mercureit.DebtCollectorBFF.dto.DossierCreationDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDto;
//import com.mercureit.DebtCollectorBFF.dto.DossierListDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDtoL;
import com.mercureit.DebtCollectorBFF.dto.PersonneForDossierCreationDto;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
//import com.mercureit.DebtCollectorBFF.projections.DossierProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IDossierService {


    Page<DossierDtoL> getAllDossiers(int page, int size,
                                     Optional<Long> idDossier,
                                     Optional<Date> dateDossier,
                                     Optional<BigDecimal> solde,
                                     Optional<String> agence,
                                     Optional<String> segment,
                                     Optional<TypePersonne> type,
                                     Optional<String> num_Identif,
                                     Optional<String> search);


    Page<DossierDto> getAllDossiersForDto(int page, int size);

//  WORKING CODE
//    Page<DossierDto> getDossiers(int page, int size,
//                                 Optional<Long> idDossier,
//                                 Optional<Date> dateDossier,
//
//                                 Optional<BigDecimal> solde,
//                                 Optional<Integer> agence,
//                                 Optional<Integer> segment,
//                                 Optional<TypePersonne> type,
//                                 Optional<String> nom,
//                                 Optional<String> nomLegal,
//                                 Optional<String> num_Identif
////                                 Optional<String> statusAffectation
//                               );

    DossierDto saveDossierD(DossierDto dossierDto);
    DossierCreationDto saveDossier(DossierCreationDto dossierDto);

    DossierDto updateDossier(Long id, DossierDto dossierDto);

    void deleteDossier(Long id);

    DossierDto findDossierById(Long id);

    DossierDto processDossierToSegment(DossierDto dossier);

}
