package com.metaverse.files.rest;

import java.util.Collections;

import com.metaverse.files.converters.auth.LoginRequestConverter;
import com.metaverse.files.converters.auth.RegistrationRequestConverter;
import com.metaverse.files.ro.auth.requests.LoginRequestRO;
import com.metaverse.files.ro.auth.requests.RegistrationRequestRO;
import com.metaverse.files.ro.auth.responses.AuthResultRO;
import com.metaverse.files.ro.response.details.ErrorDetailsRO;
import com.metaverse.files.services.auth.AuthService;
import com.metaverse.files.utils.exceptions.AuthException;
import com.metaverse.files.utils.exceptions.ExceptionCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST регистрации и авторизации пользователя.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@RestController
@Tag(name = "Сервис авторизации и регистрации", description = "Данный сервис используется для регистрации и авторизации пользователей")
@RequestMapping("/api/auth")
public class AuthRest {

    @Autowired
    private AuthService authService;
    @Autowired
    private LoginRequestConverter loginRequestConverter;
    @Autowired
    private RegistrationRequestConverter registrationRequestConverter;

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя", description = "Позволяет авторизовать пользователя и получить JWT для дальнейших обращений к сервису")
    @ApiResponse(responseCode = "200", description = "Результат авторизации", content = @Content(schema = @Schema(implementation = AuthResultRO.class)))
    public ResponseEntity<AuthResultRO> login(@RequestBody LoginRequestRO loginRequestRO) {
        String jwt = authService.login(loginRequestConverter.from(loginRequestRO));

        return ResponseEntity.ok(authResponse(jwt));
    }

    @PostMapping("/registration")
    @Operation(summary = "Регистрация пользователя", description = "Позволяет зарегистрировать пользователя и получить JWT для дальнейших обращений к сервису")
    @ApiResponse(responseCode = "200", description = "Результат регистрации", content = @Content(schema = @Schema(implementation = AuthResultRO.class)))
    public ResponseEntity<AuthResultRO> registration(@RequestBody RegistrationRequestRO registrationRequestRO) {
        String jwt = authService.registration(registrationRequestConverter.from(registrationRequestRO));

        return ResponseEntity.ok(authResponse(jwt));
    }

    private static AuthResultRO authResponse(String jwt) {
        AuthResultRO result = new AuthResultRO();
        result.setSuccess(true);
        result.setToken(jwt);

        return result;
    }

    @ExceptionHandler
    private ResponseEntity<AuthResultRO> handleAuthException(AuthException exception) {
        ErrorDetailsRO errorDetails = new ErrorDetailsRO();
        errorDetails.setExceptionMessage(exception.getMessage());
        errorDetails.setCode(ExceptionCode.UNAUTHORIZED.getCode());

        AuthResultRO resultRO = new AuthResultRO();
        resultRO.setSuccess(false);
        resultRO.setError(Collections.singletonList(errorDetails));

        return ResponseEntity.ok(resultRO);
    }
}
