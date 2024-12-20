package com.example.maktabproject.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");

        String redirectUrl = switch (role) {
            case "ROLE_ADMIN" -> "/static/adminPanel";
            case "ROLE_EXPERT" -> "/static/expertPanel";
            case "ROLE_CUSTOMER" -> "/static/customerPanel";
            default -> "/login.html?error=true";
        };

        response.sendRedirect(redirectUrl);
    }
}