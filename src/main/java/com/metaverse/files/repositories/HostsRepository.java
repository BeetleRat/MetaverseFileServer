package com.metaverse.files.repositories;

import java.util.List;
import java.util.Optional;

import com.metaverse.files.models.HostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA репозиторий для упрощенных запросов к БД хостов.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Repository
public interface HostsRepository extends JpaRepository<HostModel, Integer> {

    /**
     * Получить {@link HostModel модель хоста} по логину пользователя.
     *
     * @param login логин пользователя
     * @return {@link HostModel модель хоста}
     */
    Optional<HostModel> findByUserModelLogin(String login);

    /**
     * Получить всех {@link HostModel хостов} указанной сцены.
     *
     * @param sceneName имя сцены
     * @return список {@link HostModel хостов}
     */
    List<HostModel> findAllBySceneModelName(String sceneName);
}
