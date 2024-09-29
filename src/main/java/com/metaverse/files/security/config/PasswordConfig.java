package com.metaverse.files.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Класс конфигурации шифровки паролей.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Configuration
public class PasswordConfig {

    /**
     * Метод возвращающий шифратор.
     *
     * <p> Все шифраторы реализуют интерфейс PasswordEncoder
     *
     * @return шифратор пароля
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
