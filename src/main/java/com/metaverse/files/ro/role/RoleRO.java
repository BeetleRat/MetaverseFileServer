package com.metaverse.files.ro.role;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object роли пользователя в приложении.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Объект роли пользователя в приложении")
public class RoleRO {

    @Schema(description = "Название роли")
    private String name;
    @Schema(description = "Права (разрешения) роли")
    private Set<PermissionRO> permissions;

    /**
     * @return название роли
     */
    public String getName() {
        return name;
    }

    /**
     * @param name название роли
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return права (разрешения) роли
     */
    public Set<PermissionRO> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions права (разрешения) роли
     */
    public void setPermissions(Set<PermissionRO> permissions) {
        this.permissions = permissions;
    }
}
