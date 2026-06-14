package com.mercureit.DebtCollectorBFF.services.superset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SupersetService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String supersetUrl = "http://86.48.3.109:8088/api/v1/security";
    private final String dashboardId = "ba8d0b03-cf45-4687-a9f1-8d49622d0156" ;

    public String getGuestToken() {
        try {
            // 1. Obtenir le jeton d'accès
            String accessToken = getAccessToken();

            // 2. Obtenir le jeton invité
            return getGuestTokenFromSuperset(accessToken);
        } catch (Exception e) {
            System.err.println("Failed to fetch guest token from Superset: " + e.getMessage());
            return "dummy-guest-token";
        }
    }

    private String getAccessToken() {
        String url = supersetUrl + "/login";

        Map<String, String> body = new HashMap<>();
        body.put("username", "embedding-admin");
        body.put("password", "embedding-admin");
        body.put("provider", "db");
        body.put("refresh", "true");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        return (String) response.getBody().get("access_token");
    }

    private String getGuestTokenFromSuperset(String accessToken) {
        String url = supersetUrl + "/guest_token/";

        Map<String, Object> user = new HashMap<>();
        user.put("username", "report-viewer");
        user.put("first_name", "report-viewer");
        user.put("last_name", "report-viewer");

        Map<String, Object> resource = new HashMap<>();
        resource.put("type", "dashboard");
        resource.put("id", dashboardId);

        Map<String, Object> body = new HashMap<>();
        body.put("resources", new Map[]{resource});
        body.put("rls", new Object[]{});
        body.put("user", user);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        return (String) response.getBody().get("token");
    }
}
