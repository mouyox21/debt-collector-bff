package com.mercureit.DebtCollectorBFF.services.adresse;

import com.mercureit.DebtCollectorBFF.entities.Adresse;
import com.mercureit.DebtCollectorBFF.exception.AdresseNotFoundException;

import java.util.List;

public interface AdresseService {
     List<Adresse> getAllAdresses();
     Adresse getAdresseById(Long id) throws AdresseNotFoundException;
}
