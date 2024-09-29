package com.metaverse.files.rest;

import java.util.Collections;
import java.util.List;

import com.metaverse.files.ro.loginkey.LoginKeyRO;
import com.metaverse.files.ro.loginkey.requests.CreateLoginKeyRequestRO;
import com.metaverse.files.ro.loginkey.responses.LoginKeysResultRO;
import com.metaverse.files.ro.requests.DeleteRequestRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import com.metaverse.files.ro.response.details.InfoDetailsRO;
import com.metaverse.files.services.loginkey.LoginKeyService;
import com.metaverse.files.utils.exceptions.UselessOperationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST ключей авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@RestController
@Tag(name = "Сервис ключей авторизации", description = "Данный сервис используется для работы с ключами авторизации")
@RequestMapping("/api/login-key")
public class LoginKeyRest {

    @Autowired
    private LoginKeyService loginKeyService;

    @GetMapping("/all")
    @Operation(summary = "Получить все ключи авторизации", description = "Позволяет получить все ключи авторизации")
    @ApiResponse(responseCode = "200", description = "Список всех ключей авторизации", content = @Content(schema = @Schema(implementation = LoginKeysResultRO.class)))
    public ResponseEntity<LoginKeysResultRO> getAll() {
        List<LoginKeyRO> loginKeys = loginKeyService.getAll();

        LoginKeysResultRO result = new LoginKeysResultRO();
        result.setKeys(loginKeys);
        result.setSuccess(true);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    @Operation(summary = "Создать ключ авторизации", description = "Позволяет создавать ключи авторизации")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> create(@RequestBody CreateLoginKeyRequestRO createLoginKeyRequestRO) {
        loginKeyService.create(createLoginKeyRequestRO.getLoginKey());

        return ResponseEntity.ok(ResultDetailsRO.success());
    }


    @DeleteMapping("/delete")
    @Operation(summary = "Удалить ключ авторизации", description = "Позволяет удалить ключ авторизации с указанным id")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> delete(@RequestBody DeleteRequestRO deleteRequestRO) {
        loginKeyService.delete(deleteRequestRO.getId());

        return ResponseEntity.ok(ResultDetailsRO.success());
    }

    @ExceptionHandler
    private ResponseEntity<ResultDetailsRO> handleUselessOperationException(UselessOperationException exception) {
        InfoDetailsRO warning = new InfoDetailsRO();
        warning.setContent(exception.getMessage());

        ResultDetailsRO resultRO = new ResultDetailsRO();
        resultRO.setSuccess(true);
        resultRO.setWarning(Collections.singletonList(warning));

        return ResponseEntity.ok(resultRO);
    }
}
