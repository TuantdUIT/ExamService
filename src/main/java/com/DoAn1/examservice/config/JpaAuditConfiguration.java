package com.DoAn1.examservice.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.DoAn1.examservice.util.SecurityUtil;

@Configuration
public class JpaAuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> SecurityUtil.getCurrentUserUuid()
                .or(() -> SecurityUtil.getCurrentUserLogin())
                .or(() -> Optional.of("system"));
    }
}

