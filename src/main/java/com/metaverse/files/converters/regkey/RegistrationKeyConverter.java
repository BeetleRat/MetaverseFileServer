package com.metaverse.files.converters.regkey;

import com.metaverse.files.converters.Converter;
import com.metaverse.files.ro.regkey.RegistrationKeyRO;
import com.metaverse.files.security.models.RegistrationKeyModel;
import org.springframework.stereotype.Component;

/**
 * Конвертер RegistrationKeyModel в RegistrationKeyRO.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class RegistrationKeyConverter extends Converter<RegistrationKeyModel, RegistrationKeyRO> {

    /**
     * Конструктор.
     */
    public RegistrationKeyConverter() {
        super();
        this.from = null;
        this.to = this::convert;
    }

    private RegistrationKeyRO convert(final RegistrationKeyModel model) {
        RegistrationKeyRO ro = new RegistrationKeyRO();
        ro.setKey(model.getKey());
        ro.setOrganization(model.getOrganization());
        ro.setDateFrom(model.getDateFrom());
        ro.setDateTo(model.getDateTo());
        ro.setServerRoleId(model.getServerRole().getId());
        ro.setApplicationRoleId(model.getApplicationRole().getId());

        return ro;
    }
}
