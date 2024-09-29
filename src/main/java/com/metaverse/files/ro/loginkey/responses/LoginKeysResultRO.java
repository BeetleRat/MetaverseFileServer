package com.metaverse.files.ro.loginkey.responses;

import java.util.List;

import com.metaverse.files.ro.loginkey.LoginKeyRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Ответ на запрос получения множества ключей авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Результат запроса множества ключей авторизации")
public class LoginKeysResultRO extends ResultDetailsRO {

    @Schema(description = "Ключи авторизации")
    private List<LoginKeyRO> keys;

    /**
     * @return {@link LoginKeyRO ключи авторизации}
     */
    public List<LoginKeyRO> getKeys() {
        return keys;
    }

    /**
     * @param keys {@link LoginKeyRO ключи авторизации}
     */
    public void setKeys(List<LoginKeyRO> keys) {
        this.keys = keys;
    }
}
