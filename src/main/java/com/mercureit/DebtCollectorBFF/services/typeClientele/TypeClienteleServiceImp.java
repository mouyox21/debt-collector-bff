package com.mercureit.DebtCollectorBFF.services.typeClientele;

import com.mercureit.DebtCollectorBFF.entities.TypeClientele;
import com.mercureit.DebtCollectorBFF.exception.TypeClienteleNotFoundException;
import com.mercureit.DebtCollectorBFF.repositories.TypeClienteleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeClienteleServiceImp implements TypeClienteleService{
    @Autowired
    private TypeClienteleRepository typeClienteleRepository;
    @Override
    public List<TypeClientele> getAllTypeClientele() {
        return typeClienteleRepository.findAll();
    }

    @Override
    public TypeClientele getTypeClienteleById(Long id) throws TypeClienteleNotFoundException {
        return typeClienteleRepository.findById(id).orElseThrow(()-> new TypeClienteleNotFoundException("this TypeClientele is not found "));
    }
}
