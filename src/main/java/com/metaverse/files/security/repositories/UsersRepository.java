package com.metaverse.files.security.repositories;

import java.util.Optional;

import com.metaverse.files.security.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA репозиторий для упрощенных запросов к БД пользователей.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Repository
public interface UsersRepository extends JpaRepository<UserModel, Integer> {

    /**
     * Получить {@link UserModel модель пользователя} по логину.
     *
     * @param login логин
     * @return {@link UserModel модель пользователя}
     */
    Optional<UserModel> findByLogin(String login);
}
