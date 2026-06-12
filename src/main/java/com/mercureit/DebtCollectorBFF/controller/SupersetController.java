package com.mercureit.DebtCollectorBFF.controller;
import com.mercureit.DebtCollectorBFF.services.superset.SupersetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
    @RequestMapping("/api")
    public class SupersetController {

        @Autowired
        private SupersetService supersetService;

        @GetMapping("/get-guest-token")
        public ResponseEntity<Map<String, String>> getGuestToken() {
            Map<String, String> response = new HashMap<>();
            response.put("token", supersetService.getGuestToken());
            return ResponseEntity.ok(response);
        }

    }


