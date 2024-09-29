package com.metaverse.files.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс конфигурации cors.
 *
 * <p> В {@link SecurityConfig#securityFilterChain(HttpSecurity) SecurityConfig#securityFilterChain} отключены cors,
 * поэтому необходимо написать для них собственный кофигурационный класс.
 * В данном классе мы разрешим отправку запросов с посторонних адресов,
 * а не с того адреса, на котором запущен сервер
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;

    @Value("${application.cors.allowedOrigins}")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
