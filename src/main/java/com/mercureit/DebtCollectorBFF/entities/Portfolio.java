package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.enumuration.StatusAffectation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Portfolio {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idPortfolio;

    @Enumerated(EnumType.STRING)
    private StatusAffectation statusAffectation;

    private Date date_affectation;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    @JsonIgnore
    private Agent agent;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_dossier")
    private Dossier dossier;

    @OneToMany(mappedBy = "portfolio")
    @JsonIgnore
    private List<Event> events;

}
