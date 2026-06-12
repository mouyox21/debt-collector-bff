package com.mercureit.DebtCollectorBFF.services.devise;

import com.mercureit.DebtCollectorBFF.projections.DeviseProjection;
import com.mercureit.DebtCollectorBFF.repositories.DeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviseService implements IDeviseService{

    @Autowired
    private DeviseRepository deviseRepoitory;


    @Override
    public List<DeviseProjection> getAllDevises() {
        return deviseRepoitory.findAllDevises();
    }


//    @Override
//    public Devise getDeviseById(Long id)  {
//        return deviseRepoitory.findById(id);
//    }

}