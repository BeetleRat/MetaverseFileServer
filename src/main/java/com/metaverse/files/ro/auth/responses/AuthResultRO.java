package com.metaverse.files.ro.auth.responses;

import com.metaverse.files.ro.response.ResultDetailsRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object ответа на запрос авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Результат запроса авторизации")
public class AuthResultRO extends ResultDetailsRO {

    @Schema(description = "JSON Web Token")
    private String token;

    /**
     * @return JSON Web Token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token JSON Web Token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
