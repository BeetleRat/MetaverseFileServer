package com.metaverse.files.security.types;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Роли пользователей на сервере.
 *
 * <p> Данные роли определяют доступ пользователя к различным endpoint-ам.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public enum UserServerRole {
    // Переменные ролей хранят в себе HashSet прав(разрешений) этой роли. Для удобства.
    ADMIN(new HashSet<>(List.of(UserPermission.USER_PERMIT, UserPermission.ADMIN_PERMIT))),
    USER(new HashSet<>(List.of(UserPermission.USER_PERMIT)));

    private static final String ROLE_PREFIX = "ROLE_";
    private final Set<UserPermission> permissions;

    /**
     * Конструктор.
     *
     * @param permissions {@link UserPermission права (разрешения)} роли
     */
    UserServerRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Получить {@link UserPermission права (разрешения)} роли.
     *
     * @return {@link UserPermission права (разрешения)} роли
     */
    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    /**
     * Получить полный список {@link UserPermission прав (разрешений)} данной роли, в формате Spring Security.
     *
     * @return список {@link UserPermission прав (разрешений)} данной роли, в формате Spring Security
     */
    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> permissions =
                getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());

        permissions.add(rolePermission());

        return permissions;
    }

    private SimpleGrantedAuthority rolePermission() {
        // Право роли начинается с ROLE_
        return new SimpleGrantedAuthority(ROLE_PREFIX + this.name());
    }
}
