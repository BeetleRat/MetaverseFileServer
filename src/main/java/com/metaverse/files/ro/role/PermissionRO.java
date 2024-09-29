package com.metaverse.files.ro.role;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object права (разрешения) пользователя.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Schema(description = "Объект права (разрешения) пользователя")
public class PermissionRO {

    @Schema(description = "Название права (разрешения)")
    private String name;

    /**
     * @return название права (разрешения)
     */
    public String getName() {
        return name;
    }

    /**
     * @param name название права (разрешения)
     */
    public void setName(String name) {
        this.name = name;
    }
}
