package com.caremoa.contract.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditor implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String username = "admin";

        return Optional.of(username);
    }
}


