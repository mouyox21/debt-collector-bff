package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.enumuration.TypeIdentifiant;
import com.mercureit.DebtCollectorBFF.enumuration.TypePersonne;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "pphysique")
@PrimaryKeyJoinColumn(name = "id_pphysique")
//@Inheritance(strategy = InheritanceType.JOINED)
public class PPhysique extends Personne {

    @Column(name = "date_de_naissance")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    @Size(max = 255)
    @Column(name = "intitule")
    private String intitule;
    @Size(max = 255)
    @Column(name = "date_debut")
    private Date dateDebut;
    @Size(max = 255)
    @Column(name = "pays_naissance")
    private String paysNaissance;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 255)
    @Column(name = "nom_jeune_fille")
    private String nomJeuneFille;
    @Column(name = "date_de_deces")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeDeces;
    @Size(max = 255)
    @Column(name = "status_famille")
    private String statusFamille;
    @Size(max = 255)
    @Column(name = "sex")
    private String sex;
    @Size(max = 255)
    @Column(name = "profession")
    private String profession;
    @Size(max = 255)
    @Column(name = "situation_professionnelle")
    private String situationProfessionnelle;
    @Size(max = 255)
    @Column(name = "autre_prenom")
    private String autrePrenom;
    @Size(max = 255)
    @Column(name = "anciennete")
    private String anciennete;
    @Size(max = 255)
    @Column(name = "ville_naissance")
    private String villeNaissance;
    @Size(max = 255)
    @Column(name = "nationalite")
    private String nationalite;


    @OneToOne(mappedBy = "pPhysique")
    private ACharge aCharge;

    @ManyToOne(fetch = FetchType.EAGER) // Change to LAZY fetching
    @JoinColumn(name = "id_type_clientele")
    private TypeClientele typeClientele;

    public TypeClientele getTypeClientele() {
        return typeClientele;
    }

    public PPhysique() {
    }

    public PPhysique(String cliStatut, String nationalite, String langue, TypePersonne type, String num_Identif, TypeIdentifiant typeIdentifiant, String email, Date dateCreation, String csp, String activite, List<Dossier> dossier, List<Adresse> adresses, List<Telephone> telephone, List<PieceJointe> pieceJointes, List<Revenu> revenus, List<Charge> charges, List<AncCredit> ancCredit, LocalDate dateDeNaissance, String intitule, Date dateDebut, String paysNaissance, String nom, String prenom, String nomJeuneFille, LocalDate dateDeDeces, String statusFamille, String sex, String profession, String situationProfessionnelle, String autrePrenom, String anciennete, String villeNaissance, ACharge aCharge, TypeClientele typeClientele) {
        super(cliStatut, langue, type, num_Identif, typeIdentifiant, email, dateCreation, csp, activite, dossier, adresses, telephone, pieceJointes, revenus, charges, ancCredit);
        this.dateDeNaissance = dateDeNaissance;
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.paysNaissance = paysNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.nomJeuneFille = nomJeuneFille;
        this.dateDeDeces = dateDeDeces;
        this.statusFamille = statusFamille;
        this.sex = sex;
        this.profession = profession;
        this.situationProfessionnelle = situationProfessionnelle;
        this.autrePrenom = autrePrenom;
        this.anciennete = anciennete;
        this.villeNaissance = villeNaissance;
        this.aCharge = aCharge;
        this.typeClientele = typeClientele;
        this.nationalite = nationalite;
    }
}
