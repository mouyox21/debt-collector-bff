package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "produit")
public class Produit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idProduit;
    private String familleProduit;

//    @OneToMany(mappedBy = "produit")
//    @JsonIgnore
//    private List<Credit> creditList;

    @ManyToOne
    @JoinColumn(name = "id_credit")
    @JsonIgnore
    private Credit credit;


}
