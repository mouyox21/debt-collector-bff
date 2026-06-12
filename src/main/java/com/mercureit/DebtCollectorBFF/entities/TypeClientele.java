package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_clientele")
public class TypeClientele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_clientele")
    private Long idType;
    @Size(max = 255)
    @Column(name = "libelle_type")
    private String libelle_type;

    @OneToMany(mappedBy = "typeClientele")
    @JsonIgnore
    private List<PPhysique> pPhysiques;

}
