package com.DoAn1.examservice.config;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourcesWebConfiguration implements WebMvcConfigurer {

    @Value("${examservice.storage.root-path:D:/DoAn/DoAn1_storage}")
    private String baseURI;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String storageLocation = Path.of(baseURI)
                .toAbsolutePath()
                .normalize()
                .toUri()
                .toString();
        storageLocation = storageLocation.endsWith("/") ? storageLocation : storageLocation + "/";
        registry.addResourceHandler("/storage/**")
                .addResourceLocations(storageLocation);
    }
}
