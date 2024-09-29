package com.metaverse.files.ro.loginkey.requests;

import com.metaverse.files.ro.loginkey.LoginKeyRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object запроса создания ключа авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Запрос создания ключа авторизации")
public class CreateLoginKeyRequestRO {

    @Schema(description = "Ключ авторизации")
    private LoginKeyRO loginKey;

    /**
     * @return {@link LoginKeyRO ключи авторизации}
     */
    public LoginKeyRO getLoginKey() {
        return loginKey;
    }

    /**
     * @param loginKey {@link LoginKeyRO ключи авторизации}
     */
    public void setLoginKey(LoginKeyRO loginKey) {
        this.loginKey = loginKey;
    }
}
