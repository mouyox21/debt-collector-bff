package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;


import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "agent")
public class Agent{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agent", nullable = false)
    private int idAgent;
    @Size(max = 255)
    @Column(name = "poste")
    private String poste;
    @Size(max = 255)
    @Column(name = "departement")
    private String departement;
    @Size(max = 255)
    @Column(name = "statut_emploi")
    private String statutEmploi;
    @Column(name = "date_fin_emploi")
    private Date dateFinEmploi;
    @Size(max = 255)
    @Column(name = "superieur_hierarchique")
    private String superieurHierarchique;


    @OneToMany(mappedBy = "agent")
    private List<Portfolio> portfolios;
}
