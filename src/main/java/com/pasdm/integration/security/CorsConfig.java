package com.pasdm.integration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS to all paths
                .allowedOrigins("*") // Specific allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH") // Allowed HTTP methods
                .allowedHeaders("*") // Allows all headers
               // .allowCredentials(true) // Allows sending cookies and HTTP authentication credentials
                .maxAge(3600); // Cache preflight requests for 1 hour
    }
}