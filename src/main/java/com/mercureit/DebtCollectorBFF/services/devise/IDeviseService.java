package com.mercureit.DebtCollectorBFF.services.devise;



import com.mercureit.DebtCollectorBFF.projections.DeviseProjection;

import java.util.List;

public interface IDeviseService {


    List<DeviseProjection> getAllDevises();

//    List<DeviseDto> getDevises();

//    Devise getDeviseById(Long id) ;
}