package com.mercureit.DebtCollectorBFF.batch;

import com.mercureit.DebtCollectorBFF.dto.DossierCreationDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDtoL;
import com.mercureit.DebtCollectorBFF.services.dossier.DossierService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DossierItemWriter implements ItemWriter<DossierDto> {

    @Autowired
    private DossierService dossierService;


    @Override
    public void write(Chunk<? extends DossierDto> chunk) throws Exception {
        for (DossierDto  dossierDto : chunk) {
            dossierService.saveDossierD(dossierDto);
        }
    }
}

