package com.caremoa.contract.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AppConfig {
    @Bean
    public AuditorAware<String> auditorProvider(){
        return new SpringSecurityAuditor();
    }
}

