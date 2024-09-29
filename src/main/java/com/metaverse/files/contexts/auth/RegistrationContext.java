package com.metaverse.files.contexts.auth;

import com.metaverse.files.utils.StringUtils;

/**
 * Класс хранящий данные необходимые для входа регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public class RegistrationContext {

    private final String login;
    private final String password;
    private final String repeatPassword;
    private final String name;
    private final String registrationKey;

    private RegistrationContext(RegistrationContextBuilder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.repeatPassword = builder.repeatPassword;
        this.name = builder.name;
        this.registrationKey = builder.registrationKey;
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
     * @return повтор пароля
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Равен ли пароль повтору пароля.
     *
     * @return true, если пароли совпадают
     */
    public boolean arePasswordsMatch() {
        return StringUtils.stringEquals(password, repeatPassword);
    }

    /**
     * Различаются ли пароль и повтор пароля.
     *
     * @return true, если пароли различаются
     */
    public boolean passwordsNotMatch() {
        return !arePasswordsMatch();
    }

    /**
     * @return отображаемое имя пользователя
     */
    public String getName() {
        return name;
    }

    /**
     * @return ключ регистрации
     */
    public String getRegistrationKey() {
        return registrationKey;
    }

    /**
     * @return builder
     */
    public static RegistrationContextBuilder builder() {
        return new RegistrationContextBuilder();
    }

    /**
     * Builder.
     */
    public static class RegistrationContextBuilder {

        private String login;
        private String password;
        private String repeatPassword;
        private String name;
        private String registrationKey;

        /**
         * @param login login
         * @return self
         */
        public RegistrationContextBuilder login(String login) {
            this.login = login;
            return this;
        }

        /**
         * @param password пароль
         * @return self
         */
        public RegistrationContextBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * @param repeatPassword повтор пароля
         * @return self
         */
        public RegistrationContextBuilder repeatPassword(String repeatPassword) {
            this.repeatPassword = repeatPassword;
            return this;
        }

        /**
         * @param name отображаемое имя пользователя
         * @return self
         */
        public RegistrationContextBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * @param registrationKey ключ регистрации
         * @return self
         */
        public RegistrationContextBuilder registrationKey(String registrationKey) {
            this.registrationKey = registrationKey;
            return this;
        }

        /**
         * Собрать объект.
         *
         * @return собранный объект
         */
        public RegistrationContext build() {
            return new RegistrationContext(this);
        }
    }
}
