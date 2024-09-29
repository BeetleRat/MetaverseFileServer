package com.metaverse.files.services.user;

import com.metaverse.files.ro.user.UserRO;

/**
 * Сервис пользователей.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public interface UserService {

    /**
     * Получить имя пользователя по логину пользователя.
     *
     * @param login логин пользователя
     * @return имя пользователя
     */
    String getNameByLogin(String login);

    /**
     * Получить  информация о пользователе по логину пользователя.
     *
     * @param login логин пользователя
     * @return информация о пользователе
     */
    UserRO getInfoByLogin(String login);
}
