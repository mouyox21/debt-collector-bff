package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Table(name = "telephone")
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telephone_seq")
    @SequenceGenerator(name = "telephone_seq", sequenceName = "telephone_id_telephone_seq", allocationSize = 1)
    @Column(name = "id_telephone")
    private Long id;

    @Size(max = 255)
    @Column(name = "numero_telephone")
    private String numeroTelephone;

    @Size(max = 255)
    @Column(name = "operateur_telephonique")
    private String operateurTelephonique;

    @Size(max = 255)
    @Column(name = "pays")
    private String pays;

    @Size(max = 255)
    @Column(name = "proprietaire_numero")
    private String proprietaireNumero;

    @Size(max = 255)
    @Column(name = "type_numero")
    private String typeNumero;

    @Column(name = "indicatif_regional", precision = 38)
    private String indicatifRegional;

    @ManyToOne
    @JoinColumn(name = "id_personne")
    @JsonIgnore
    private Personne personne;

}
