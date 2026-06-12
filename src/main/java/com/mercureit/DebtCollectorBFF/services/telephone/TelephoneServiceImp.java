package com.mercureit.DebtCollectorBFF.services.telephone;

import com.mercureit.DebtCollectorBFF.entities.Telephone;
import com.mercureit.DebtCollectorBFF.exception.TelephoneNotFoundException;
import com.mercureit.DebtCollectorBFF.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TelephoneServiceImp implements TelephoneService{
    @Autowired
    TelephoneRepository telephoneRepository;
    @Override
    public List<Telephone> getAllTelephones() {
        return telephoneRepository.findAll();
    }

    @Override
    public Telephone getTelephoneById(Long id) throws TelephoneNotFoundException {
        return telephoneRepository.findById(id).orElseThrow(() -> new TelephoneNotFoundException("this Telephone is not found "));
    }
}
