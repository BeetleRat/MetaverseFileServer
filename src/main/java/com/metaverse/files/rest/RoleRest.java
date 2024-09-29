package com.metaverse.files.rest;

import java.util.Collections;
import java.util.List;

import com.metaverse.files.ro.requests.DeleteRequestRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import com.metaverse.files.ro.response.details.InfoDetailsRO;
import com.metaverse.files.ro.role.RoleRO;
import com.metaverse.files.ro.role.requests.CreateRoleRequestRO;
import com.metaverse.files.ro.role.responses.RolesResultRO;
import com.metaverse.files.services.role.RoleService;
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
 * REST ролей.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@RestController
@Tag(name = "Сервис ролей", description = "Данный сервис используется для работы с ролями пользователей в приложении")
@RequestMapping("/api/role")
public class RoleRest {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    @Operation(summary = "Получить все роли", description = "Позволяет получить все роли пользователей в приложении")
    @ApiResponse(responseCode = "200", description = "Список всех ролей в приложении", content = @Content(schema = @Schema(implementation = RolesResultRO.class)))
    public ResponseEntity<RolesResultRO> getAll() {
        List<RoleRO> roles = roleService.getAll();

        RolesResultRO result = new RolesResultRO();
        result.setRoles(roles);
        result.setSuccess(true);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    @Operation(summary = "Создать или изменить роль", description = "Позволяет создавать роли или изменять права уже существующих ролей пользователей в приложении")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> create(@RequestBody CreateRoleRequestRO createRoleRequestRO) {
        roleService.update(createRoleRequestRO.getRole());

        return ResponseEntity.ok(ResultDetailsRO.success());
    }


    @DeleteMapping("/delete")
    @Operation(summary = "Удалить ключ авторизации", description = "Позволяет удалить ключ авторизации с указанным id")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> delete(@RequestBody DeleteRequestRO deleteRequestRO) {
        roleService.delete(deleteRequestRO.getId());

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
