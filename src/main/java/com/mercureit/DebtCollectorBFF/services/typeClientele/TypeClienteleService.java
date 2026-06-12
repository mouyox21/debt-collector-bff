package com.mercureit.DebtCollectorBFF.services.typeClientele;

import com.mercureit.DebtCollectorBFF.entities.TypeClientele;
import com.mercureit.DebtCollectorBFF.exception.TypeClienteleNotFoundException;

import java.util.List;

public interface TypeClienteleService {
    List<TypeClientele> getAllTypeClientele();
    TypeClientele getTypeClienteleById(Long id) throws TypeClienteleNotFoundException;
}
