package com.mercureit.DebtCollectorBFF.services.segment;

import java.util.List;

import com.mercureit.DebtCollectorBFF.dto.SegmentDto;
import com.mercureit.DebtCollectorBFF.dto.SegmentMaxAttDTO;
import com.mercureit.DebtCollectorBFF.entities.PhaseSegment;
import com.mercureit.DebtCollectorBFF.entities.Segment;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
import com.mercureit.DebtCollectorBFF.projections.SegmentProjection;
import com.mercureit.DebtCollectorBFF.repositories.PhaseSegmentRepository;
import com.mercureit.DebtCollectorBFF.repositories.SegmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentService implements ISegmentService {

    private static final Logger log = LoggerFactory.getLogger(SegmentService.class);

    @Autowired
    SegmentRepository segmentRepository;
    @Autowired
    PhaseSegmentRepository phaseSegmentRepository;
    @Autowired
    IMapClassWithDto<Segment, SegmentDto> segmentMapping;




//    @Override
//    public List<SegmentProjection> getAllSegments() {
//        return segmentRepository.findAllSegments();
//    }



    @Override
    public List<SegmentDto> getSegments() {
        List<Segment> segments = segmentRepository.findAll();
        return segmentMapping.convertListToListDto(segments, SegmentDto.class);
    }

    @Override
    public List<SegmentProjection> findAllSegments() {
        return segmentRepository.findAllSegments();
    }

    @Override
    public List<SegmentMaxAttDTO> getAllSegments() {
        return segmentRepository.findAllSegmentMaxAttDTOs();
    }

    @Override
    public Segment createSegment(Segment segment) {
        PhaseSegment phaseSegment = phaseSegmentRepository.findById(segment.getPhaseSegment().getIdPhase()).orElseThrow(() -> new EntityNotFoundException("PhaseSegment not found"));
        segment.setPhaseSegment(phaseSegment);
        return segmentRepository.save(segment);
    }

    @Override
    public Segment updateSegment(Integer id, Segment segment){
        return segmentRepository.save(segment);
    }

    @Override
    public void deleteSegment(Long id){
        segmentRepository.deleteById(id);
    }
   @Override
   public SegmentMaxAttDTO getSegmentById(Long id){
       return segmentRepository.findSegmentByIdSegment(id);
   }
    public List<PhaseSegment> getAllPhasesSegment(){
        return phaseSegmentRepository.findAll();
    }
}
