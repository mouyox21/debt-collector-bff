package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private Long id;
    @Column(name = "date_occupation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOccupation;
    @Column(name = "etage")
    private Integer etage;
    @Column(name = "n_appartement")
    private Integer nAppartement;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 255)
    @Column(name = "code_postal")
    private String codePostal;
    @Size(max = 255)
    @Column(name = "nom_batiment")
    private String nomBatiment;
    @Size(max = 255)
    @Column(name = "pays")
    private String pays;
    @Size(max = 255)
    @Column(name = "region")
    private String region;
    @Size(max = 255)
    @Column(name = "rue")
    private String rue;
    @Size(max = 255)
    @Column(name = "situation_occupation")
    private String situationOccupation;
    @Size(max = 255)
    @Column(name = "type_adresse")
    private String typeAdresse;
    @Size(max = 255)
    @Column(name = "ville")
    private String ville;

    @ManyToOne
    @JoinColumn(name = "id_personne")
    @JsonIgnore
    private Personne personne;
}
