package com.mercureit.DebtCollectorBFF.batch;

import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.services.dossier.DossierService;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class DossierItemReader implements ItemReader<DossierDto> {

    @Autowired
    private DossierService dossierService;

    private int currentPage = 0;
    private final int pageSize = 10; // Define your page size
    private List<DossierDto> currentBatch = new ArrayList<DossierDto>();
    private Iterator<DossierDto> currentIterator;


    @Override
    public DossierDto read() throws Exception {
        if (currentIterator == null || !currentIterator.hasNext()) {
            loadNextBatch();
        }
        return currentIterator != null && currentIterator.hasNext() ? currentIterator.next() : null;
    }

    private void loadNextBatch() {
        Page<DossierDto> page = dossierService.getAllDossiersForDto(currentPage, pageSize);
        currentBatch = page.getContent();
        currentIterator = currentBatch.iterator();
        currentPage++;
    }

}


