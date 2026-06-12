package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agence")
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agence", nullable = false)
    private Long idAgence;
    @Size(max = 255)
    @Column(name = "nom_agence")
    private String nomAgence;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 255)
    @Column(name = "n_telephone")
    private String nTelephone;
    @Size(max = 255)
    @Column(name = "adresse_email")
    private String adresseEmail;
    @Size(max = 255)
    @Column(name = "directeur_agence")
    private String directeurAgence;
    @Column(name = "date_creation")
    private Date dateCreation;


    @ManyToOne
    @JoinColumn(name = "id_entite")
    @JsonIgnore
    private Entite entite;
    @OneToMany(mappedBy = "agence")
    private List<Dossier> dossierList;
}
