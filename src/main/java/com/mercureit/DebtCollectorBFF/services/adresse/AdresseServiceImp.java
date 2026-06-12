package com.mercureit.DebtCollectorBFF.services.adresse;


import com.mercureit.DebtCollectorBFF.entities.Adresse;
import com.mercureit.DebtCollectorBFF.exception.AdresseNotFoundException;
import com.mercureit.DebtCollectorBFF.repositories.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseServiceImp implements AdresseService {
  @Autowired
  private AdresseRepository adresseRepository;
    @Override
    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    @Override
    public Adresse getAdresseById(Long id)throws AdresseNotFoundException {
        return adresseRepository.findById(id).orElseThrow(() -> new AdresseNotFoundException("this Adress is not found "));
    }

}

