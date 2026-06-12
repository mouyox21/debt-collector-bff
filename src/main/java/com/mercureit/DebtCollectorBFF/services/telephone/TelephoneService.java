package com.mercureit.DebtCollectorBFF.services.telephone;

import com.mercureit.DebtCollectorBFF.entities.Adresse;
import com.mercureit.DebtCollectorBFF.entities.Telephone;
import com.mercureit.DebtCollectorBFF.exception.TelephoneNotFoundException;

import java.util.List;

public interface TelephoneService {

    List<Telephone> getAllTelephones();
    Telephone getTelephoneById(Long id) throws TelephoneNotFoundException;
}
