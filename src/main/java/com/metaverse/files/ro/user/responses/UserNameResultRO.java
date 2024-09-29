package com.metaverse.files.ro.user.responses;

import com.metaverse.files.ro.response.ResultDetailsRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Ответ на запрос имени пользователя.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Результат получения имени пользователя")
public class UserNameResultRO extends ResultDetailsRO {

    @Schema(description = "Имя пользователя")
    private String name;

    /**
     * @return имя пользователя
     */
    public String getName() {
        return name;
    }

    /**
     * @param name имя пользователя
     */
    public void setName(String name) {
        this.name = name;
    }
}
