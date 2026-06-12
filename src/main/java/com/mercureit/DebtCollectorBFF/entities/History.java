package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Long idHisory;

    @Column(name = "id_dossier")
    @JsonProperty("id_dossier")
    private Long id_Dossier;

    @Column(name = "change_date")
    @JsonProperty("change_date")
    private Date change_date;

    @Column(name = "id_modificateur")
    @JsonProperty("id_modificateur")
    private Long id_modificateur;

}
