package com.metaverse.files.repositories;

import java.util.Optional;

import com.metaverse.files.models.LoginKeyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA репозиторий для упрощенных запросов к БД ключей входа.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Repository
public interface LoginKeyRepository extends JpaRepository<LoginKeyModel, Integer> {

    /**
     * Получить {@link LoginKeyModel модель ключа входа} по ключу.
     *
     * @param key ключ
     * @return {@link LoginKeyModel модель ключа входа}
     */
    Optional<LoginKeyModel> findByKey(String key);

    /**
     * Получить {@link LoginKeyModel модель ключа входа} по id.
     *
     * @param id id
     * @return {@link LoginKeyModel модель ключа входа}
     */
    Optional<LoginKeyModel> findById(int id);
}
