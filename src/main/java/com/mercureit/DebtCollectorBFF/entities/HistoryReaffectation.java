package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "history_reaffectation")
@PrimaryKeyJoinColumn(name = "id_history")
public class HistoryReaffectation extends History{


    @Column(name = "previous_agent_id")
    @JsonProperty("previous_agent_id")
    private Long previous_agent_id;
    @Column(name = "new_agent_id")
    @JsonProperty("new_agent_id")
    private Long new_agent_id;



}
