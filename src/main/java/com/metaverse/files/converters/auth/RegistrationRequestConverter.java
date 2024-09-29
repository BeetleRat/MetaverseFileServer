package com.metaverse.files.converters.auth;

import com.metaverse.files.contexts.auth.RegistrationContext;
import com.metaverse.files.converters.Converter;
import com.metaverse.files.ro.auth.requests.RegistrationRequestRO;
import org.springframework.stereotype.Component;

/**
 * Конвертер RegistrationRequestRO в RegistrationContext.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class RegistrationRequestConverter extends Converter<RegistrationContext, RegistrationRequestRO> {

    public RegistrationRequestConverter() {
        super();
        this.from = this::covert;
        this.to = null;
    }

    private RegistrationContext covert(final RegistrationRequestRO request) {
        return RegistrationContext.builder()
                .login(request.getLogin())
                .name(request.getName())
                .password(request.getPassword())
                .repeatPassword(request.getRepeatPassword())
                .registrationKey(request.getRegistrationKey())
                .build();
    }
}
