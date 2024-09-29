package com.metaverse.files.converters.loginkey;

import com.metaverse.files.converters.Converter;
import com.metaverse.files.models.LoginKeyModel;
import com.metaverse.files.ro.loginkey.LoginKeyRO;
import org.springframework.stereotype.Component;

/**
 * Конвертер LoginKeyModel в LoginKeyRO.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class LoginKeyConverter extends Converter<LoginKeyModel, LoginKeyRO> {

    /**
     * Конструктор.
     */
    public LoginKeyConverter() {
        super();
        this.from = null;
        this.to = this::convert;
    }

    private LoginKeyRO convert(LoginKeyModel model) {
        LoginKeyRO ro = new LoginKeyRO();
        ro.setKey(model.getKey());
        ro.setDateFrom(model.getDateFrom());
        ro.setDateTo(model.getDateTo());

        return ro;
    }
}
