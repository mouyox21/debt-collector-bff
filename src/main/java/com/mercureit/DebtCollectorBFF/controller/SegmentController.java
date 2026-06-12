package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.dto.AgentDto;
import com.mercureit.DebtCollectorBFF.dto.SegmentDto;
import com.mercureit.DebtCollectorBFF.dto.SegmentMaxAttDTO;
import com.mercureit.DebtCollectorBFF.entities.PhaseSegment;
import com.mercureit.DebtCollectorBFF.entities.Segment;
import com.mercureit.DebtCollectorBFF.projections.SegmentProjection;
import com.mercureit.DebtCollectorBFF.repositories.PhaseSegmentRepository;
import com.mercureit.DebtCollectorBFF.services.segment.ISegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/segment", produces = MediaType.APPLICATION_JSON_VALUE)
public class SegmentController {

    private static final Logger log = LoggerFactory.getLogger(SegmentController.class);

    @Autowired
    private ISegmentService segmentService;
    @Autowired
    private PhaseSegmentRepository phaseSegmentRepository;

    @GetMapping("/segments")
    public List<SegmentProjection> getAllSegment() {
        return segmentService.findAllSegments();
    }

    @GetMapping("/segmentsAllAtt")
    public List<SegmentMaxAttDTO> getAllSegmentsAllAtt() {
        return segmentService.getAllSegments();
    }
    //@RolesAllowed("admin")
    @GetMapping("/listSegments")
    public ResponseEntity<List<SegmentDto>> getAllSegments() {
        List<SegmentDto> segmentDto = segmentService.getSegments();
        return ResponseEntity.ok(segmentDto);
    }
    @GetMapping("/getSegment/{id}")
    public SegmentMaxAttDTO getSegmentById(@PathVariable Long id){
        return segmentService.getSegmentById(id);
    }
   /* @PostMapping("/createSegment/{phaseId}")
    public ResponseEntity<Segment> saveSegment(@PathVariable Long phaseId, @RequestBody Segment segment) {
        Optional<PhaseSegment> phaseSegment = phaseSegmentRepository.findById(phaseId);
        if (phaseSegment.isPresent()) {
            segment.setPhaseSegment(phaseSegment.get());
            Segment createdSegment = segmentService.createSegment(segment);
            return new ResponseEntity<>(createdSegment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
   @PostMapping("/createSegment")
   public ResponseEntity<Segment> saveSegment(@RequestBody Segment segment) {
       System.out.println(segment.getPhaseSegment().getIdPhase());
       System.out.println(segment.getIdSegment());
     Segment createdSegment = segmentService.createSegment(segment);
    return new ResponseEntity<>(createdSegment, HttpStatus.CREATED);
   }
    @GetMapping("/getPhasesSegment")
    public List<PhaseSegment> getAllPhasesSegment(){
        return segmentService.getAllPhasesSegment();
    }
    @PutMapping("/updateSegment/{id}")
    public ResponseEntity<Segment> updateSegment(@RequestBody Segment segment, @PathVariable Integer id) {
        SegmentMaxAttDTO segmentFound = segmentService.getSegmentById(id.longValue());
        if (segmentFound == null) {
            return ResponseEntity.notFound().build();
        }
        segment.setIdSegment(id);
        segmentService.updateSegment(id, segment);
        System.out.println(segment.getDescription());
        return ResponseEntity.noContent().build();
    }
}
