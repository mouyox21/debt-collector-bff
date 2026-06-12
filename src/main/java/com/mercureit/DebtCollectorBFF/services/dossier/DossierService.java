package com.mercureit.DebtCollectorBFF.services.dossier;

import com.mercureit.DebtCollectorBFF.dto.*;
//import com.mercureit.DebtCollectorBFF.dto.DossierListDto;
import com.mercureit.DebtCollectorBFF.entities.Devise;
import com.mercureit.DebtCollectorBFF.entities.Dossier;
import com.mercureit.DebtCollectorBFF.entities.Event;
import com.mercureit.DebtCollectorBFF.entities.Segment;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import com.mercureit.DebtCollectorBFF.listeners.CustomAgendaEventListener;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
//import com.mercureit.DebtCollectorBFF.repositories.DossierDtoRepository;
//import com.mercureit.DebtCollectorBFF.projections.DossierProjection;
import com.mercureit.DebtCollectorBFF.repositories.DossierLRepository;
import com.mercureit.DebtCollectorBFF.repositories.DossierRepository;
import com.mercureit.DebtCollectorBFF.repositories.EventRepository;
import com.mercureit.DebtCollectorBFF.services.event.EventService;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DossierService implements IDossierService {

    private static final Logger log = LoggerFactory.getLogger(DossierService.class);

    @Autowired
    DossierRepository dossierRepository;

    @Autowired
    DossierLRepository dossierLRepository;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private AgendaEventListener agendaEventListener;

    @Autowired
    private EventService eventService;

//    @Autowired
//    DossierDtoRepository dossierDtoRepository;

    @Autowired
    IMapClassWithDto<Dossier, DossierDto> dossierMapping;

    @Autowired
    IMapClassWithDto<Segment, SegmentDto> segmentMapping;

    @Autowired
    IMapClassWithDto<Dossier, DossierCreationDto> dossierCMapping;

    @Autowired
    IMapClassWithDto<Event, EventDto> eventMapping;


    @Autowired
    IMapClassWithDto<Dossier, DossierDtoL> dossierLMapping;


    @Override
    public DossierDto processDossierToSegment(DossierDto dossierDto) {

        Dossier dossierEntity = dossierMapping.convertToEntity(dossierDto, Dossier.class);
        System.out.println("check segment: " + dossierDto);
        System.out.println("Before rules execution: " + dossierEntity);
        System.out.println("check segment associate with Dossier : " + dossierEntity.getSegment());

        KieSession kieSession = kieContainer.newKieSession();
        CustomAgendaEventListener customAgendaEventListener = new CustomAgendaEventListener();
        kieSession.addEventListener(customAgendaEventListener);
        try {
            kieSession.insert(dossierEntity);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilReferenceDate = sdf.parse("2024-01-01");
            java.util.Date utilCreationDate = sdf.parse("2020-01-01");

            java.sql.Date referenceDate = new java.sql.Date(utilReferenceDate.getTime());
            java.sql.Date creationDate = new java.sql.Date(utilCreationDate.getTime());


            // Set the global variable for the date variable on drools file
            kieSession.setGlobal("referenceDate", referenceDate);

            // Set focus to agenda group "lowProvision" and fire all rules in this group
            kieSession.getAgenda().getAgendaGroup("lowProvision").setFocus();
            kieSession.fireAllRules();

            // Set focus to agenda group "oldDossierHighProvision" and fire all rules in this group
            kieSession.getAgenda().getAgendaGroup("oldDossierHighProvision").setFocus();
            kieSession.fireAllRules();

            // Set focus to agenda group "PersonneSegment" and fire all rules in this group
            kieSession.getAgenda().getAgendaGroup("PersonneSegment").setFocus();
            kieSession.fireAllRules();

            System.out.println("get Dossier ID :" + dossierEntity.getIdDossier());
            System.out.println("get Personne assoiciated with Dossier :" + dossierEntity.getPersonne());
            System.out.println("After rules execution: " + dossierEntity + " dossier ID : " + dossierEntity.getIdDossier());

            // Retrieve matched rule names
            List<String> matchedRuleNames = customAgendaEventListener.getMatchedRules();
            System.out.println("Matched Rule Names: " + matchedRuleNames);

            if(matchedRuleNames.contains("Rule 1")){
                createEvent("Call the personne");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            kieSession.dispose();
        }
        Long dossierID = dossierEntity.getIdDossier();

        DossierDto updatedDossierDto = dossierMapping.convertToDto(dossierEntity, DossierDto.class);
        DossierDto updateDossier = updateDossier(dossierID, updatedDossierDto);
        return updateDossier;
    }


    private void createEvent(String text) {
        Event event = new Event();
        event.setStartDate(LocalDateTime.now());
        event.setEndDate(LocalDateTime.now());
        event.setText(text);

        eventService.createEvent(event);
        System.out.println("event created" + event);
    }
    public Page<DossierDtoL> getAllDossiers(int page, int size,
                                            Optional<Long> idDossier,
                                            Optional<Date> dateDossier,
                                            Optional<BigDecimal> solde,
                                            Optional<String> agence,
                                            Optional<String> segment,
                                            Optional<TypePersonne> type,
                                            Optional<String> num_Identif,
                                            Optional<String> search) {
        Pageable pageable = PageRequest.of(page, size);
        return dossierLRepository.findAllDossierProjections(idDossier, dateDossier, solde, agence, segment, type, num_Identif, search, pageable);

    }

    public Page<DossierDto> getAllDossiersForDto(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dossierRepository.findAllDossierProjections(pageable);
    }










//   WORKING CODE
//    @Override
//    public Page<DossierDto> getDossiers(int page, int size,
//                                        Optional<Long> idDossier,
//                                        Optional<Date> dateDossier,
//
//                                        Optional<BigDecimal> solde,
//                                        Optional<Integer> agence,
//                                        Optional<Integer> segment,
//                                        Optional<TypePersonne> type,
//                                        Optional<String> nom,
//                                        Optional<String> nomLegal,
//                                        Optional<String> num_Identif) {
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        // Use LocalDate directly in the repository method call
//        Page<Dossier> dossierPage = dossierRepository.findDossiersWithFilters(idDossier, dateDossier,
//                 solde, agence, segment,type, nom, nomLegal, num_Identif, pageable);
//
//        return dossierPage.map(dossier -> dossierMapping.convertToDto(dossier, DossierDto.class));
//    }


    @Override
    public DossierDto findDossierById(Long id) {
        return dossierMapping.convertToDto( dossierRepository.findById(id).get() , DossierDto.class );
    }



    @Override
    public DossierCreationDto saveDossier(DossierCreationDto dossierDto) {

        Dossier dossier = dossierRepository.save(dossierCMapping.convertToEntity(dossierDto, Dossier.class));
        return dossierCMapping.convertToDto(dossier, DossierCreationDto.class);
    }

    @Override
    public DossierDto saveDossierD(DossierDto dossierDto) {

        Dossier dossier = dossierRepository.save(dossierMapping.convertToEntity(dossierDto, Dossier.class));
        return dossierMapping.convertToDto(dossier, DossierDto.class);
    }



    @Override
    public DossierDto updateDossier(Long id, DossierDto dossierDto) {
        Dossier existingDossier = dossierRepository.findById(id).orElse(null);
        SegmentDto dossierSegmentDto = dossierDto.getSegment();
        Segment segmentEntity = segmentMapping.convertToEntity(dossierSegmentDto, Segment.class);
        if (existingDossier != null) {
            existingDossier.setDateProvision(dossierDto.getDateProvision());
            existingDossier.setMontantProvision(dossierDto.getMontantProvision());
            existingDossier.setSolde(dossierDto.getSolde());
            existingDossier.setSegment(segmentEntity);


            if (dossierDto.getDevise() != null) {
                existingDossier.setDevise(new Devise());
                existingDossier.getDevise().setIdDevise(dossierDto.getDevise().getIdDevise());
            }


            Dossier updatedDossier = dossierRepository.save(existingDossier);
            return dossierMapping.convertToDto(updatedDossier, DossierDto.class);
        }
        return null;
    }


    @Override
    public void deleteDossier(Long id) {
        dossierRepository.deleteById(id);
    }


}