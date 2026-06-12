package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.dto.PortfolioDAPDTO;
import com.mercureit.DebtCollectorBFF.dto.PortfolioDto;
import com.mercureit.DebtCollectorBFF.entities.Portfolio;
import com.mercureit.DebtCollectorBFF.services.portfolio.IPortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioController {

    private static final Logger log = LoggerFactory.getLogger(PortfolioController.class);

    @Autowired
    IPortfolioService portfolioService;

    @GetMapping("/portfolios")
    public ResponseEntity<Page<PortfolioDto>> getAllPortfolios(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<PortfolioDto> portfolioPage = portfolioService.getPortfolios(page, size);

        return ResponseEntity.ok(portfolioPage);
    }

    @GetMapping("/agent/{idAgent}")
    public ResponseEntity<Page<Portfolio>> getPortfoliosByAgentId(
            @PathVariable int idAgent,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<Portfolio> portfoliosPage = portfolioService.getPortfoliosByAgentId(idAgent, page, size);
        return ResponseEntity.ok(portfoliosPage);
    }

    @GetMapping("/dossier/{idAgent}")
    public ResponseEntity<Page<PortfolioDAPDTO>> getDossierWithAssociateAgentId(
            @PathVariable Long idAgent,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<PortfolioDAPDTO> portfolioDossierPage = portfolioService.getDossiersWithAgentIdAndSearch(idAgent, search, PageRequest.of(page, size));
        return ResponseEntity.ok(portfolioDossierPage);
    }
}
