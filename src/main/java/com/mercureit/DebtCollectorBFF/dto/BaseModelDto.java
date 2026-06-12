package com.mercureit.DebtCollectorBFF.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseModelDto {
    private Long id;
    private String uuid;
    private String code;
}
