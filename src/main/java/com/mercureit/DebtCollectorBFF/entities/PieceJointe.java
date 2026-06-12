package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercureit.DebtCollectorBFF.enumuration.TypeIdentifiant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"personne.pieceJointes"})
@Table(name = "piece_jointe")
public class PieceJointe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_file")
    private int idFile;
    @Size(max = 255)
    @Column(name = "nom_file")
    private String nomFile;
    @Size(max = 255)
    @Column(name = "type_file")
    private String typeFile;
    @Column(name = "taille_file")
    private Long tailleFile;
    @Column(name = "date_creation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;
    @Size(max = 255)
    @Column(name = "path")
    private String path;
    @Size(max = 255)
    @Column(name = "status_file")
    private String statusFile;
    @Column(name = "type_piecejoindre")
    private String typePiecejoindre;
    @Size(max = 255)
    @Column(name = "permission_access")
    private String permissionAccess;

    @ManyToOne
    @JoinColumn(name = "id_personne")
    @JsonIgnore
    private Personne personne;
    @ManyToOne
    @JoinColumn(name = "id_dossier")
    @JsonIgnore
    private Dossier dossier;
}
