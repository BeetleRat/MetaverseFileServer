package com.metaverse.files.ro.role.responses;

import java.util.List;

import com.metaverse.files.ro.response.ResultDetailsRO;
import com.metaverse.files.ro.role.RoleRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Ответ на запрос получения множества ролей.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Schema(description = "Результат запроса множества ролей")
public class RolesResultRO extends ResultDetailsRO {

    @Schema(description = "Ключи авторизации")
    private List<RoleRO> roles;

    /**
     * @return {@link RoleRO роли}
     */
    public List<RoleRO> getRoles() {
        return roles;
    }

    /**
     * @param roles {@link RoleRO роли}
     */
    public void setRoles(List<RoleRO> roles) {
        this.roles = roles;
    }
}
