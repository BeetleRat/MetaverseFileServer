package com.metaverse.files.converters.auth;

import com.metaverse.files.contexts.auth.LoginContext;
import com.metaverse.files.converters.Converter;
import com.metaverse.files.ro.auth.requests.LoginRequestRO;
import org.springframework.stereotype.Component;

/**
 * Конвертер LoginRequestRO в LoginContext.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class LoginRequestConverter extends Converter<LoginContext, LoginRequestRO> {

    /**
     * Конструктор.
     */
    public LoginRequestConverter() {
        super();
        this.from = this::convert;
        this.to = null;
    }

    private LoginContext convert(final LoginRequestRO loginRequest) {
        return LoginContext.builder()
                .login(loginRequest.getLogin())
                .password(loginRequest.getPassword())
                .loginKey(loginRequest.getLoginKey())
                .build();
    }
}
