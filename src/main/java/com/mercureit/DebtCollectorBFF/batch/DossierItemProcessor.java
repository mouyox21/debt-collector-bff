package com.mercureit.DebtCollectorBFF.batch;

import com.mercureit.DebtCollectorBFF.dto.DossierCreationDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.services.dossier.DossierService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DossierItemProcessor implements ItemProcessor<DossierDto, DossierDto> {

    @Autowired
    private DossierService dossierService;

    @Override
    public DossierDto process(DossierDto dossierDto) throws Exception {
        return dossierService.processDossierToSegment(dossierDto);
    }
}
