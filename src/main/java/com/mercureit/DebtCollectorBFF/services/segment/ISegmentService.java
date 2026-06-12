package com.mercureit.DebtCollectorBFF.services.segment;

import com.mercureit.DebtCollectorBFF.dto.SegmentDto;

import com.mercureit.DebtCollectorBFF.dto.SegmentMaxAttDTO;
import com.mercureit.DebtCollectorBFF.entities.PhaseSegment;
import com.mercureit.DebtCollectorBFF.entities.Segment;
import com.mercureit.DebtCollectorBFF.projections.SegmentProjection;

import java.util.List;

public interface ISegmentService {
    List<SegmentDto> getSegments();

    List<SegmentProjection> findAllSegments();
    public List<SegmentMaxAttDTO> getAllSegments();
    public Segment createSegment(Segment segment);
    public Segment updateSegment(Integer id, Segment segment);
    public void deleteSegment(Long id);
    public SegmentMaxAttDTO getSegmentById(Long id);
    public List<PhaseSegment> getAllPhasesSegment();
}
