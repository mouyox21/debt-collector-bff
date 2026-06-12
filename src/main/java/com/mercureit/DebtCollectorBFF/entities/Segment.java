package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercureit.DebtCollectorBFF.enumuration.EtatSegement;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_segment", nullable = false)
    private Integer idSegment;

    @Column(name = "nom_segment")
    private String nomSegment;

    private String description;
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private EtatSegement etat;

    private String Abreviation;
    @JsonIgnore
    @OneToMany(mappedBy ="segment")
    private List<Dossier> dossierList;

    @ManyToOne
    @JoinColumn(name = "id_phase")
    private PhaseSegment phaseSegment;


}
