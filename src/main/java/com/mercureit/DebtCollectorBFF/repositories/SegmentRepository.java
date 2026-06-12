package com.mercureit.DebtCollectorBFF.repositories;

import com.mercureit.DebtCollectorBFF.dto.SegmentMaxAttDTO;
import com.mercureit.DebtCollectorBFF.entities.Segment;
import com.mercureit.DebtCollectorBFF.projections.SegmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SegmentRepository extends JpaRepository<Segment, Long> {

    @Query("SELECT s.idSegment AS idSegment, s.nomSegment  AS nomSegment FROM Segment  s")
    List<SegmentProjection> findAllSegments();

    @Query("SELECT new com.mercureit.DebtCollectorBFF.dto.SegmentMaxAttDTO(" +
            "s.idSegment, s.nomSegment, s.description, s.etat, s.Abreviation, s.phaseSegment) " +
            "FROM Segment s")
    List<SegmentMaxAttDTO> findAllSegmentMaxAttDTOs();
    @Query("SELECT new com.mercureit.DebtCollectorBFF.dto.SegmentMaxAttDTO(" +
            "s.idSegment, s.nomSegment, s.description, s.etat, s.Abreviation, s.phaseSegment) " +
            "FROM Segment s " +
            "JOIN s.phaseSegment ps " +
            "WHERE s.idSegment = :id")
    SegmentMaxAttDTO findSegmentByIdSegment(@Param("id") Long id);
}
