package com.metaverse.files.ro.auth.requests;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object запроса авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Запрос на авторизацию")
public class LoginRequestRO {

    @Schema(description = "Login пользователя")
    private String login;
    @Schema(description = "Пароль пользователя")
    private String password;
    @Schema(description = "Ключ авторизации пользователя. Не обязателен для суперпользователей")
    private String loginKey;

    /**
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return ключ авторизации пользователя
     */
    public String getLoginKey() {
        return loginKey;
    }

    /**
     * @param loginKey ключ авторизации пользователя
     */
    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }
}
