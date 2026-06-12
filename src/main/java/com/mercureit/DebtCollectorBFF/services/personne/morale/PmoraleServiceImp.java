package com.mercureit.DebtCollectorBFF.services.personne.morale;

import com.mercureit.DebtCollectorBFF.dto.PmoraleDTO;
import com.mercureit.DebtCollectorBFF.entities.*;
import com.mercureit.DebtCollectorBFF.exception.PersonneNotFoundException;
import com.mercureit.DebtCollectorBFF.mappers.MapClassWithDto;
import com.mercureit.DebtCollectorBFF.repositories.personne.PmoraleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmoraleServiceImp implements PmoraleService {
    @Autowired
    private PmoraleRepository pmoraleRepository;
    @Autowired
    private MapClassWithDto<PMorale, PmoraleDTO> pmoraleMapping;



    @Override
    public PmoraleDTO savePmorale(PmoraleDTO pmoraleDTO)throws PersonneNotFoundException{
        PMorale pMorale = pmoraleMapping.convertToEntity(pmoraleDTO, PMorale.class);

        for (Adresse adresse : pMorale.getAdresses()) {
            adresse.setPersonne(pMorale);
        }
        for (Telephone telephone : pMorale.getTelephone()) {
            telephone.setPersonne(pMorale);
        }
//        for (Dirigeant dirigeant : pMorale.getDirigeants()) {
//            dirigeant.setPMorale(pMorale);
//        }
        for (PieceJointe pieceJointe : pMorale.getPieceJointes()) {
            pieceJointe.setPersonne(pMorale);
        }
        for (Revenu revenu : pMorale.getRevenus()) {
            revenu.setPersonne(pMorale);
        }
        for (AncCredit ancCredit : pMorale.getAncCredit()) {
            ancCredit.setPersonne(pMorale);
        }
//        for (Dossier dossier : pMorale.getDossier()) {
//            dossier.setPersonne(pMorale);
//        }
        PMorale savedPmorale = pmoraleRepository.save(pMorale);
        return pmoraleMapping.convertToDto(savedPmorale, PmoraleDTO.class);
    }



}
