package com.metaverse.files.contexts.auth;

/**
 * Класс хранящий данные необходимые для входа в систему.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public class LoginContext {

    private final String login;
    private final String password;
    private final String loginKey;

    private LoginContext(LoginContextBuilder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.loginKey = builder.loginKey;
    }

    /**
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return ключ авторизации
     */
    public String getLoginKey() {
        return loginKey;
    }

    /**
     * @return builder
     */
    public static LoginContextBuilder builder() {
        return new LoginContextBuilder();
    }

    /**
     * Builder.
     */
    public static class LoginContextBuilder {

        private String login;
        private String password;
        private String loginKey;

        /**
         * @param login login
         * @return self
         */
        public LoginContextBuilder login(String login) {
            this.login = login;
            return this;
        }

        /**
         * @param password пароль
         * @return self
         */
        public LoginContextBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * @param loginKey ключ авторизации
         * @return self
         */
        public LoginContextBuilder loginKey(String loginKey) {
            this.loginKey = loginKey;
            return this;
        }

        /**
         * Собрать объект.
         *
         * @return собранный объект
         */
        public LoginContext build() {
            return new LoginContext(this);
        }
    }
}
