package com.mercureit.DebtCollectorBFF.services.personne;


import com.mercureit.DebtCollectorBFF.dto.PersonneDTO;
import com.mercureit.DebtCollectorBFF.dto.PersonneDtoFDCreation;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PersonneService {
    Personne savePersonne(Personne personne) throws PersonneNotFoundException;
    //Page<PersonneDTO> listPersonnes(int page , int size);
    Personne getPersonneById(Long id) throws PersonneNotFoundException;

    List<PersonneDtoFDCreation> getPersonneByNum_Identif(String num_Identif);



    Personne updatePersonne(Personne requestDTO, Long id) throws PersonneNotFoundException;
    Personne deletePersonne(Long id) throws PersonneNotFoundException;
    Page<PersonneDTO> listPersonnes(Pageable pageable, Long refClient, String type, String nomRS, String cinICE, String telephone, String ville);
}
