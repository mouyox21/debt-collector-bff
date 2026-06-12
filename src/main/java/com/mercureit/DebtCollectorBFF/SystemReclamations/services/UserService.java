package com.mercureit.DebtCollectorBFF.SystemReclamations.services;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * Retrieves the email of the currently authenticated user.
     *
     * @param user The currently authenticated user injected by Spring Security.
     * @return The email address of the currently authenticated user.
     */
    public String getCurrentUserEmail(@AuthenticationPrincipal OidcUser user) {
        if (user != null) {
            return user.getEmail();
        } else {
            throw new IllegalStateException("No authenticated user found.");
        }
    }
}
