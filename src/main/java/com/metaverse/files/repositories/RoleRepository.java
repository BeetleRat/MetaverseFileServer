package com.metaverse.files.repositories;

import java.util.Optional;

import com.metaverse.files.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA репозиторий для упрощенных запросов к БД ролей приложения.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

    /**
     * Найти роль по id.
     *
     * @param id id
     * @return роль пользователя в приложении
     */
    Optional<RoleModel> findById(int id);

    /**
     * Найти роль по названию.
     *
     * @param name название роли
     * @return роль пользователя в приложении
     */
    Optional<RoleModel> findByName(String name);
}
