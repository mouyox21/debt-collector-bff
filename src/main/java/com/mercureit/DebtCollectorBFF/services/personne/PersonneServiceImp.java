package com.mercureit.DebtCollectorBFF.services.personne;

import com.mercureit.DebtCollectorBFF.dto.PersonneDTO;
import com.mercureit.DebtCollectorBFF.dto.PersonneDtoFDCreation;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;
import com.mercureit.DebtCollectorBFF.mappers.MapClassWithDto;
import com.mercureit.DebtCollectorBFF.repositories.personne.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneServiceImp implements PersonneService{
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private MapClassWithDto<Personne, PersonneDTO> mapPersonneWithDto;

    @Override
    public Personne savePersonne(Personne personne) throws PersonneNotFoundException {
        if (personne.getType() != null) {
            try {
                personne.setType(personne.getType().toString());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid type value for Personne");
            }
        } else {
            throw new IllegalArgumentException("Personne type cannot be null");
        }
        return personneRepository.save(personne);
    }





    @Override
    public List<PersonneDtoFDCreation> getPersonneByNum_Identif(String num_Identif){


        return personneRepository.findByNum_Identif(num_Identif);
    }



    /*@Override
    public Page<PersonneDTO> listPersonnes(int page , int size) {
        Page<PersonneDTO> personnesPage = personneRepository.findAllPersonnesWithDetails(PageRequest.of(page, size));
*//*
        return mapPersonneWithDto.mapEntityPageIntoDtoPage(personnesPage, PersonneDTO.class);
*//*
        return personnesPage;
    }*/

    @Override
    public Personne getPersonneById(Long id) throws PersonneNotFoundException {
        return personneRepository.findById(id).orElseThrow(()->new PersonneNotFoundException(String.format("this credit is not found ")));
    }

    @Override
    public Personne updatePersonne(Personne requestDTO, Long id) throws PersonneNotFoundException {
        return null;
    }

    @Override
    public Personne deletePersonne(Long id) throws PersonneNotFoundException {
        Personne personne = this.getPersonneById(id);
        this.personneRepository.delete(personne);
        return personne;
    }
    @Override
    public Page<PersonneDTO> listPersonnes(Pageable pageable, Long refClient, String type, String nomRS, String cinICE, String telephone, String ville) {
        return personneRepository.filterPersonnes(pageable, refClient, type, nomRS, cinICE, telephone, ville);
    }
}
