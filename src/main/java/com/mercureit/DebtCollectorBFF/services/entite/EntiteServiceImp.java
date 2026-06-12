package com.mercureit.DebtCollectorBFF.services.entite;

import com.mercureit.DebtCollectorBFF.entities.Entite;
import com.mercureit.DebtCollectorBFF.exception.EntiteNotFoundException;
import com.mercureit.DebtCollectorBFF.projections.EntiteProjection;
import com.mercureit.DebtCollectorBFF.repositories.EntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntiteServiceImp implements EntiteService{
    @Autowired
    private EntiteRepository entiteRepoitory;
    @Override
    public List<EntiteProjection> getAllEntites() {
        return entiteRepoitory.findAllEntiteIdAndNom();
    }
    @Override
    public Entite getEntiteById(Long id) throws EntiteNotFoundException {
        return entiteRepoitory.findById(id).orElseThrow(()-> new EntiteNotFoundException("Entite Not Found"));
    }
}
