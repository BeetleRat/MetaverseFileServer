package com.metaverse.files.rest;

import java.util.Collections;
import java.util.List;

import com.metaverse.files.ro.regkey.RegistrationKeyRO;
import com.metaverse.files.ro.regkey.requests.CreateRegistrationKeyRequestRO;
import com.metaverse.files.ro.regkey.responses.RegistrationKeysResultRO;
import com.metaverse.files.ro.requests.DeleteRequestRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import com.metaverse.files.ro.response.details.ErrorDetailsRO;
import com.metaverse.files.ro.response.details.InfoDetailsRO;
import com.metaverse.files.services.regkey.RegistrationKeyService;
import com.metaverse.files.utils.exceptions.ExceptionCode;
import com.metaverse.files.utils.exceptions.InvalidRequestStateException;
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
 * REST ключей регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@RestController
@Tag(name = "Сервис ключей регистрации", description = "Данный сервис используется для работы с ключами регистрации")
@RequestMapping("/api/registration-key")
public class RegistrationKeyRest {

    @Autowired
    private RegistrationKeyService regKeyService;

    @PostMapping("/create")
    @Operation(summary = "Создать ключ регистрации", description = "Позволяет создавать ключи регистрации")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> create(@RequestBody CreateRegistrationKeyRequestRO request) {
        regKeyService.create(request.getRegistrationKey());

        return ResponseEntity.ok(ResultDetailsRO.success());
    }


    @GetMapping("/all")
    @Operation(summary = "Получить все ключи регистрации", description = "Позволяет получить все ключи регистрации")
    @ApiResponse(responseCode = "200", description = "Список всех ключей регистрации", content = @Content(schema = @Schema(implementation = RegistrationKeysResultRO.class)))
    public ResponseEntity<RegistrationKeysResultRO> getAll() {
        List<RegistrationKeyRO> regKeys = regKeyService.getAll();

        RegistrationKeysResultRO result = new RegistrationKeysResultRO();
        result.setKeys(regKeys);
        result.setSuccess(true);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Удалить ключ регистрации", description = "Позволяет удалить ключ регистрации с указанным id")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> delete(@RequestBody DeleteRequestRO deleteRequestRO) {
        regKeyService.delete(deleteRequestRO.getId());

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

    @ExceptionHandler
    private ResponseEntity<ResultDetailsRO> handleInvalidRequestStateException(InvalidRequestStateException exception) {
        ErrorDetailsRO error = new ErrorDetailsRO();
        error.setExceptionMessage(exception.getMessage());
        error.setCode(ExceptionCode.BAD_REQUEST.getCode());

        ResultDetailsRO resultRO = new ResultDetailsRO();
        resultRO.setSuccess(false);
        resultRO.setError(Collections.singletonList(error));

        return ResponseEntity.ok(resultRO);
    }
}
