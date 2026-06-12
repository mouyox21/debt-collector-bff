package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PhaseSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phase", nullable = false)
    private Long idPhase;
    @Size(max = 255)
    @Column(name = "libelle_phase")
    private String libelle_phase;

    @OneToMany(mappedBy = "phaseSegment")
    @JsonIgnore
    private List<Segment> segmentList;

}
