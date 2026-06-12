package com.mercureit.DebtCollectorBFF.services.portfolio;

import com.mercureit.DebtCollectorBFF.dto.PortfolioDAPDTO;
import com.mercureit.DebtCollectorBFF.dto.PortfolioDossierDTO;
import com.mercureit.DebtCollectorBFF.dto.PortfolioDto;
import com.mercureit.DebtCollectorBFF.entities.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPortfolioService {
    Page<PortfolioDto> getPortfolios(int page, int size);

    Page<Portfolio> getPortfoliosByAgentId(int agentId, int page, int size);

    Page<PortfolioDAPDTO> getDossiersWithAgentIdAndSearch(Long idAgent, String search, Pageable pageable);
}
