package com.metaverse.files.services.auth;

import com.metaverse.files.contexts.auth.LoginContext;
import com.metaverse.files.contexts.auth.RegistrationContext;
import com.metaverse.files.utils.exceptions.AuthException;

/**
 * Сервис авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public interface AuthService {

    /**
     * Авторизовать пользователя.
     *
     * @param ctx {@link LoginContext контекст авторизации}
     * @return JSON Web Token
     * @throws AuthException если не удалось авторизовать пользователя
     */
    String login(LoginContext ctx);

    /**
     * Зарегистрировать пользователя.
     *
     * @param ctx {@link RegistrationContext контекст регистрации}
     * @return JSON Web Token
     * @throws AuthException если не удалось зарегистрировать пользователя
     */
    String registration(RegistrationContext ctx);
}
