package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigInteger;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "devise")
public class Devise {
    @Id
    @ColumnDefault("nextval('devise_id_devise_seq'")
    @Column(name = "id_devise", nullable = false)
    private int idDevise;
    @Column(name = "code_devise", precision = 38)
    private BigInteger codeDevise;
    @Size(max = 255)
    @Column(name = "nom_devise")
    private String nomDevise;
    @Size(max = 255)
    @Column(name = "symbole_devise")
    private String symboleDevise;
    @Size(max = 255)
    @Column(name = "pays_origine")
    private String paysOrigine;
    @Column(name = "taux_de_change", precision = 38)
    private BigInteger tauxDeChange;
    @Column(name = "date_derniere_mise_a")
    private Date dateDerniereMiseA;
    @Size(max = 255)
    @Column(name = "symbole_de_sousunit")
    private String symboleDeSousunit;

    @OneToMany(mappedBy = "devise")
    @JsonIgnore
    private List<Dossier> dossierList;



}
