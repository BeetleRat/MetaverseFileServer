package com.metaverse.files.ro.role.requests;

import com.metaverse.files.ro.role.RoleRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object запроса создания роли в приложении.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Schema(description = "Запрос создания роли")
public class CreateRoleRequestRO {

    @Schema(description = "Роль")
    private RoleRO role;

    /**
     * @return {@link RoleRO роль}
     */
    public RoleRO getRole() {
        return role;
    }

    /**
     * @param role {@link RoleRO роль}
     */
    public void setRole(RoleRO role) {
        this.role = role;
    }
}
