package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Data
@Entity
@Table(name = "match")
public class Match {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_match")
    private Long idMatch;
    @Column(name = "montant_regle")
    @JsonProperty("montant_regle")
    private BigInteger montant_regle;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_paiement")
    private Paiement paiement;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_element_financier")
    private ElementFinancier elementFinanciers;
}
