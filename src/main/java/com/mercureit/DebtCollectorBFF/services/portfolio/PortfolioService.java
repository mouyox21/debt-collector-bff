package com.mercureit.DebtCollectorBFF.services.portfolio;

import com.mercureit.DebtCollectorBFF.dto.PortfolioDAPDTO;
import com.mercureit.DebtCollectorBFF.dto.PortfolioDto;
import com.mercureit.DebtCollectorBFF.entities.Portfolio;
import com.mercureit.DebtCollectorBFF.mappers.IMapClassWithDto;
import com.mercureit.DebtCollectorBFF.repositories.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService implements IPortfolioService {

    private static final Logger log = LoggerFactory.getLogger(PortfolioService.class);

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    IMapClassWithDto<Portfolio, PortfolioDto> portfolioMapping;

    @Override
    public Page<PortfolioDto> getPortfolios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Portfolio> portfoliosPage = portfolioRepository.findAll(pageable);

        return portfoliosPage.map(portfolio -> portfolioMapping.convertToDto(portfolio, PortfolioDto.class));
    }

    @Override
    public Page<Portfolio> getPortfoliosByAgentId(int agentId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return portfolioRepository.findByAgentId(agentId, pageable);
    }

    @Override
    public Page<PortfolioDAPDTO> getDossiersWithAgentIdAndSearch(Long idAgent, String search, Pageable pageable) {
        return portfolioRepository.findDossiersWithAgentIdAndSearch(idAgent, search, pageable);
    }
}
