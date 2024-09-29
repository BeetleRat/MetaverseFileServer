package com.metaverse.files.repositories;

import java.util.Optional;

import com.metaverse.files.security.models.RegistrationKeyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA репозиторий для упрощенных запросов к БД ключей регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Repository
public interface RegistrationKeyRepository extends JpaRepository<RegistrationKeyModel, Integer> {

    /**
     * Получить {@link RegistrationKeyModel модель ключа регистрации} по ключу.
     *
     * @param key ключ
     * @return {@link RegistrationKeyModel модель ключа регистрации}
     */
    Optional<RegistrationKeyModel> findByKey(String key);

    /**
     * Получить {@link RegistrationKeyModel модель ключа регистрации} по id.
     *
     * @param id id
     * @return {@link RegistrationKeyModel модель ключа регистрации}
     */
    Optional<RegistrationKeyModel> findById(int id);
}
