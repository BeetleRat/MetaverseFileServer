package com.metaverse.files.ro.regkey.responses;

import java.util.List;

import com.metaverse.files.ro.regkey.RegistrationKeyRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Ответ на запрос получения всех ключей регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Результат запроса множества ключей регистрации")
public class RegistrationKeysResultRO extends ResultDetailsRO {

    @Schema(description = "Ключи регистрации")
    private List<RegistrationKeyRO> keys;

    /**
     * @return {@link RegistrationKeyRO ключи регистрации}
     */
    public List<RegistrationKeyRO> getKeys() {
        return keys;
    }

    /**
     * @param keys {@link RegistrationKeyRO ключи регистрации}
     */
    public void setKeys(List<RegistrationKeyRO> keys) {
        this.keys = keys;
    }
}
