package com.mercureit.DebtCollectorBFF.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercureit.DebtCollectorBFF.enumuration.MoyenePaiement;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


import java.util.Date;

@Entity
@Data
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    private Long idPaiement;

    @Column(name = "montant_paye")
    @JsonProperty("montant_paye")
    private BigDecimal montant_paye;

    @Column(name = "date_paiement")
    @JsonProperty("date_paiement")
    private Date date_paiement;

    @Enumerated(EnumType.STRING)
    @Column(name = "moyen_paiement")
    @JsonProperty("moyen_paiement")
    private MoyenePaiement moyenPaiement;


    @OneToMany(mappedBy = "paiement")
    private List<Match> matches;
    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="MATCH")
    @ToString.Exclude
    private List<ElementFinancier> elementFinanciers = new ArrayList<ElementFinancier>();*/
}
