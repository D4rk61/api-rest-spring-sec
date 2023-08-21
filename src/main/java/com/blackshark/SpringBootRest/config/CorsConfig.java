package com.blackshark.SpringBootRest.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    // configuracion de cors para controladores
    // esto se aplicara a todos los controladores

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // configuracion de cors
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));    // origenes a permitir
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // metodos a permitir
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));    // todos los encabezados permitir a partir de cors

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}

