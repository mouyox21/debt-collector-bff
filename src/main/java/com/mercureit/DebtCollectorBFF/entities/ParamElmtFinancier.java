package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "param_element_financier")
public class ParamElmtFinancier {
    @Id
    @ColumnDefault("nextval('param_elmt_financier_id_param_seq'")
    @Column(name = "id_param", nullable = false)
    private Long idParam;
    @Size(max = 255)
    @Column(name = "param_name")
    private String  param_name;
    @Size(max = 255)
    @Column(name = "param_value")
    private String  param_value;
    @ManyToOne
    @JsonIgnore
    private ElementFinancier elementFinancier;
}
