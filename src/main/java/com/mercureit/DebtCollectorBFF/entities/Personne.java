package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mercureit.DebtCollectorBFF.enumuration.TypeIdentifiant;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)

@Table(name = "personne")
public  class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne", nullable = false, updatable = false)
    private Long id;
    @Size(max = 255)
    @Column(name = "cli_statut")
    private String cliStatut;
//    @Size(max = 255)
//    @Column(name = "nationalite")
//    private String nationalite;
    @Size(max = 255)
    @Column(name = "langue")
    private String langue;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_personne")
    private TypePersonne type;
    @Size(max = 255)
    @Column(name = "num_identif")
    private String numIdentif;
    @Enumerated(EnumType.STRING)
    private TypeIdentifiant typeIdentifiant;
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Column(name = "date_creation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreation;
    @Size(max = 255)
    @Column(name = "csp")
    private String csp;
    @Size(max = 255)
    @Column(name = "activite")
    private String activite;


//    @OneToMany(mappedBy = "personne")
//    @ToString.Exclude
//    @JsonIgnore
//    private List<Dossier> dossier;
    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Adresse> adresses;
    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telephone> telephone;
    @OneToMany(mappedBy = "personne",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PieceJointe> PieceJointes;
    @OneToMany(mappedBy = "personne",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Revenu> revenus;
    @OneToMany(mappedBy = "personne",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    List<Charge> charges;
    @OneToMany(mappedBy = "personne",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    List <AncCredit> ancCredit;

    public void setType(String typeStr) {
        this.type = TypePersonne.valueOf(typeStr.toUpperCase());  // Conversion vers l'énumération
    }

    public Personne() {
    }

    public Personne(String cliStatut, String langue, TypePersonne type, String numIdentif, TypeIdentifiant typeIdentifiant, String email, Date dateCreation, String csp, String activite, List<Dossier> dossier, List<Adresse> adresses, List<Telephone> telephone, List<PieceJointe> pieceJointes, List<Revenu> revenus, List<Charge> charges, List<AncCredit> ancCredit) {
        this.cliStatut = cliStatut;

        this.langue = langue;
        this.type = type;
        this.numIdentif = numIdentif;
        this.typeIdentifiant = typeIdentifiant;
        this.email = email;
        this.dateCreation = dateCreation;
        this.csp = csp;
        this.activite = activite;
        //this.dossier = dossier;
        this.adresses = adresses;
        this.telephone = telephone;
        PieceJointes = pieceJointes;
        this.revenus = revenus;
        this.charges = charges;
        this.ancCredit = ancCredit;
    }
}
