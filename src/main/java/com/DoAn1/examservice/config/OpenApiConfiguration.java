package com.DoAn1.examservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI examServiceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Exam Service API")
                        .version("1.0.0")
                        .description("Microservice for question banks, exams, assignments, attempts, scoring, and proctoring."))
                .servers(List.of(new Server().url("http://localhost:8080").description("Local")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication",
                        new SecurityScheme()
                                .name("Bearer Authentication")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}

