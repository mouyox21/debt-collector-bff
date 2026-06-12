package com.mercureit.DebtCollectorBFF.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "history_segmentation")
@PrimaryKeyJoinColumn(name = "id_history")
public class HistorySegmentation extends History{

    @Column(name = "previous_segment_id")
    @JsonProperty("previous_segment_id")
    private Long previous_segment_id;
    @Column(name = "new_segment_id")
    @JsonProperty("new_segment_id")
    private Long new_segment_id;



}
