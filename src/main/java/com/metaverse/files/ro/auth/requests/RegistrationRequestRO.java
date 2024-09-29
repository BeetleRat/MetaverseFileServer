package com.metaverse.files.ro.auth.requests;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object запроса регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public class RegistrationRequestRO {

    @Schema(description = "Login пользователя")
    private String login;
    @Schema(description = "Пароль пользователя")
    private String password;
    @Schema(description = "Повторный пароль пользователя")
    private String repeatPassword;
    @Schema(description = "Отображаемое имя пользователя")
    private String name;
    @Schema(description = "Ключ регистрации пользователя")
    private String registrationKey;

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
     * @return повторный пароль пользователя
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * @param repeatPassword повторный пароль пользователя
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * @return отображаемое имя пользователя
     */
    public String getName() {
        return name;
    }

    /**
     * @param name отображаемое имя пользователя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ключ регистрации пользователя
     */
    public String getRegistrationKey() {
        return registrationKey;
    }

    /**
     * @param registrationKey ключ регистрации пользователя
     */
    public void setRegistrationKey(String registrationKey) {
        this.registrationKey = registrationKey;
    }
}
