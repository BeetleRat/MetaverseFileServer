package com.metaverse.files.repositories;

import java.util.Optional;

import com.metaverse.files.models.SceneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA репозиторий для упрощенных запросов к БД сцен.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Repository
public interface SceneRepository extends JpaRepository<SceneModel, Integer> {

    /**
     * Получить {@link SceneModel модель сцены} по имени.
     *
     * @param name имя сцены
     * @return {@link SceneModel модель сцены}
     */
    Optional<SceneModel> findByName(String name);
}
