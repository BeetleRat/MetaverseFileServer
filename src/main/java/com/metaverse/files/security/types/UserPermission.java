package com.metaverse.files.security.types;

/**
 * Права (разрешения) пользователя.
 *
 * <p>Переменные прав(разрешений) хранят в себе строку с именем данного разрешения.
 * Т.к. для создания права(разрешения) в формате Spring Security требуется строка
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public enum UserPermission {
    USER_PERMIT("permit:user"),
    ADMIN_PERMIT("permit:admin");

    private final String permission;

    /**
     * Конструктор.
     *
     * @param permission строка с именем права (разрешения)
     */
    UserPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Получить строку с именем права (разрешения).
     *
     * @return строка с именем права (разрешения)
     */
    public String getPermission() {
        return permission;
    }
}
