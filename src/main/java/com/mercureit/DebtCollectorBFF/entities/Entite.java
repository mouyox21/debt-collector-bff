package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Entite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entite", nullable = false)
    private Long idEntite;
    @Size(max = 255)
    @Column(name = "type_entite")
    private String typeEntite;
    @Size(max = 255)
    @Column(name = "nom_entite")
    private String nomEntite;
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
    @Column(name = "identifiant_fiscal")
    private String identifiantFiscal;
    @Column(name = "date_creation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "entite")
    private List<AncCredit> ancCredits;
    @OneToMany(mappedBy = "entite")
    private List<Agence>agences;

}
