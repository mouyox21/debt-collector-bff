package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercureit.DebtCollectorBFF.enumuration.TypeIdentifiant;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "pmorale")
@PrimaryKeyJoinColumn(name = "id_pmorale")
public class PMorale extends Personne{
    @Column(name = "date_de_creation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeCreation;
    @Size(max = 255)
    @Column(name = "nom_legal")
    private String nomLegal;
    @Size(max = 255)
    @Column(name = "forme_juridique")
    private String formeJuridique;
    @Size(max = 255)
    @Column(name = "ville_constitution")
    private String villeConstitution;
    @Size(max = 255)
    @Column(name = "pays_constitution")
    private String paysConstitution;
    @Size(max = 255)
    @Column(name = "nom_enseigne")
    private String nomEnseigne;
    @Column(name = "n_rcs", precision = 38)
    private BigInteger nRcs;
    @Column(name = "n_if", precision = 38)
    private BigInteger nIf;
    @Column(name = "nombre_salaries", precision = 38)
    private BigInteger nombreSalaries;
    @Column(name = "type_commerce")
    private String typeCommerce;
    @Column(name ="type_societe")
    private String typeSociete;

    @OneToMany(mappedBy = "pMorale",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dirigeant> dirigeants;

    public PMorale() {
    }

    public PMorale(String cliStatut, String langue, TypePersonne type, String numIdentif, TypeIdentifiant typeIdentifiant, String email, Date dateCreation, String csp, String activite, List<Dossier> dossier, List<Adresse> adresses, List<Telephone> telephone, List<PieceJointe> pieceJointes, List<Revenu> revenus, List<Charge> charges, List<AncCredit> ancCredit, LocalDate dateDeCreation, String nomLegal, String formeJuridique, String villeConstitution, String nomEnseigne, BigInteger nRcs, BigInteger nIf, BigInteger nombreSalaries, List<Dirigeant> dirigeants) {
        super(cliStatut, langue, type, numIdentif, typeIdentifiant, email, dateCreation, csp, activite, dossier, adresses, telephone, pieceJointes, revenus, charges, ancCredit);
        this.dateDeCreation = dateDeCreation;
        this.nomLegal = nomLegal;
        this.formeJuridique = formeJuridique;
        this.villeConstitution = villeConstitution;
        this.nomEnseigne = nomEnseigne;
        this.nRcs = nRcs;
        this.nIf = nIf;
        this.nombreSalaries = nombreSalaries;
        this.dirigeants = dirigeants;
    }
}
