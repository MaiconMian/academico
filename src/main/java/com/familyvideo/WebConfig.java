package com.familyvideo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Permite a origem do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite esses métodos
                .allowedHeaders("*") // Permite qualquer cabeçalho
                .allowCredentials(true); // Permite enviar cookies, caso necessário
    }
}



