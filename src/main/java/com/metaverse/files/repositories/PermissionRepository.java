package com.metaverse.files.repositories;

import java.util.List;
import java.util.Set;

import com.metaverse.files.models.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA репозиторий для упрощенных запросов к БД прав (разрешений) пользователя в приложении.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Repository
public interface PermissionRepository extends JpaRepository<PermissionModel, Integer> {

    /**
     * Получить права, имена которых хранятся в заданном наборе.
     *
     * @param names набор имен прав
     * @return права, имена которых хранятся в заданном наборе
     */
    List<PermissionModel> findAllByNameIn(Set<String> names);
}
