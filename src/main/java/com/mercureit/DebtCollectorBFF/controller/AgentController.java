//package com.mercureit.DebtCollectorBFF.controller;
//
//import com.mercureit.DebtCollectorBFF.dto.AgentDto;
//import com.mercureit.DebtCollectorBFF.services.Agent.IAgentService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/agent", produces = MediaType.APPLICATION_JSON_VALUE)
//public class AgentController {
//
//    private static final Logger log = LoggerFactory.getLogger(AgentController.class);
//
//    @Autowired
//    IAgentService agentService;
//
//    //@RolesAllowed("admin")
//    @GetMapping("/agents")
//    public ResponseEntity<List<AgentDto>> getAllAgents() {
//
//        List<AgentDto> agentDto = agentService.getAgents();
//
//        return ResponseEntity.ok(agentDto);
//    }
//
//
//    @GetMapping("/{id}")
//    public AgentDto getAgent(@PathVariable Long id){
//        return agentService.findAgentById(id);
//    }
//
//
//}
