package com.metaverse.files.security.config;

import com.metaverse.files.security.jwt.JwtRequestFilter;
import com.metaverse.files.security.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Класс конфигурации spring security.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Шифратор паролей.
     */
    @Autowired
    private PasswordConfig passwordConfig;
    /**
     * Фильтр обрабатывающий JWT токен.
     */
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    /**
     * Сервис для получения пользователей из БД.
     */
    @Autowired
    private SecurityUserService securityUsersService;

    /**
     * Метод настройки доступа к endpoint-ам.
     *
     * @param http {@link HttpSecurity}
     * @return {@link SecurityFilterChain}
     * @throws Exception при ошибке конфигурации доступа
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .cors().and() // Включаем cors
                .csrf().disable()
                // Настраиваем доступ к endpoint-ам
                .authorizeHttpRequests(this::setupAccess)
                // Отключаем стандартные сессии
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Если пользователь попытался совершить запрос,
                // на который у него нет прав
                // то сгенерировать исключение UNAUTHORIZED
                .exceptionHandling()
                .authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                )
                .and()
                // Добавляем созданный фильтр
                // перед фильтром UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private void setupAccess(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth.requestMatchers("/api/Auth/*").permitAll();

        swaggerAccess(auth);

        auth.anyRequest().permitAll();
    }

    private static void swaggerAccess(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth.requestMatchers("/swagger-ui").permitAll();
        auth.requestMatchers("/swagger-ui/*").permitAll();
        auth.requestMatchers("/v3/api-docs").permitAll();
        auth.requestMatchers("/v3/api-docs/*").permitAll();
    }

    /**
     * {@link DaoAuthenticationProvider} bean.
     *
     * <p> Создание и настройка объекта,
     * предоставляющего доступ к сервису взаимодействия с хранимыми пользователями.
     *
     * @return {@link DaoAuthenticationProvider}
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordConfig.passwordEncoder());
        provider.setUserDetailsService(securityUsersService);

        return provider;
    }

    /**
     * {@link AuthenticationManager} bean.
     *
     * <p> AuthenticationManager проверяет корректность данных безопасности.
     *
     * @param authenticationConfiguration {@link AuthenticationConfiguration конфигуратор}
     * @return {@link AuthenticationManager}
     * @throws Exception если при конфигурации произошла ошибка
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
