package com.metaverse.files.ro.regkey.requests;

import com.metaverse.files.ro.regkey.RegistrationKeyRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object запроса создания ключа регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Запрос создания ключа регистрации")
public class CreateRegistrationKeyRequestRO {

    @Schema(description = "Ключ регистрации")
    private RegistrationKeyRO registrationKey;

    /**
     * @return {@link RegistrationKeyRO ключи регистрации}
     */
    public RegistrationKeyRO getRegistrationKey() {
        return registrationKey;
    }

    /**
     * @param registrationKey {@link RegistrationKeyRO ключи регистрации}
     */
    public void setRegistrationKey(RegistrationKeyRO registrationKey) {
        this.registrationKey = registrationKey;
    }
}
