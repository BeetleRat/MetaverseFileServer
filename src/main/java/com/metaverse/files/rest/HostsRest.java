package com.metaverse.files.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.metaverse.files.converters.host.CreateHostRequestConverter;
import com.metaverse.files.ro.host.HostRO;
import com.metaverse.files.ro.host.requests.CreateHostRequestRO;
import com.metaverse.files.ro.host.responses.MultisceneHostsResultRO;
import com.metaverse.files.ro.host.responses.SinglesceneHostsResultRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import com.metaverse.files.ro.response.details.ErrorDetailsRO;
import com.metaverse.files.ro.response.details.InfoDetailsRO;
import com.metaverse.files.services.host.HostsService;
import com.metaverse.files.utils.exceptions.DataNotFoundException;
import com.metaverse.files.utils.exceptions.ExceptionCode;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST хостов.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@RestController
@Tag(name = "Сервис хостов", description = "Данный сервис используется для работы с информацией о хостах")
@RequestMapping("/api/hosts")
public class HostsRest {

    @Autowired
    private HostsService hostsService;
    @Autowired
    private CreateHostRequestConverter createHostRequestConverter;

    @GetMapping("/all")
    @Operation(summary = "Получить всех хостов", description = "Позволяет получить всех хостов сгруппированных по имени сцены, для которой они являются хостами")
    @ApiResponse(responseCode = "200", description = "Хосты сгруппированные по имени комнаты", content = @Content(schema = @Schema(implementation = MultisceneHostsResultRO.class)))
    public ResponseEntity<MultisceneHostsResultRO> getHosts() {
        Map<String, List<HostRO>> hostsGroupedByScene = hostsService.hostsGroupedByScene();

        MultisceneHostsResultRO resultRO = new MultisceneHostsResultRO();
        resultRO.setHosts(hostsGroupedByScene);
        resultRO.setSuccess(true);

        return ResponseEntity.ok(resultRO);
    }

    @GetMapping(params = {"sceneName"})
    @Operation(summary = "Получить хостов указанной сцены", description = "Позволяет получить список всех хостов сцены указанной в пути запроса")
    @ApiResponse(responseCode = "200", description = "Список хостов указанной комнаты", content = @Content(schema = @Schema(implementation = SinglesceneHostsResultRO.class)))
    public ResponseEntity<SinglesceneHostsResultRO> getHostsByScene(@RequestParam("sceneName") String sceneName) {
        List<HostRO> hosts = hostsService.hostsByScene(sceneName);

        SinglesceneHostsResultRO resultRO = new SinglesceneHostsResultRO();
        resultRO.setHosts(hosts);
        resultRO.setSuccess(true);

        return ResponseEntity.ok(resultRO);
    }

    @PostMapping("/create")
    @Operation(summary = "Создать хоста", description = "Позволяет создать хоста из авторизованного в данный момент пользователя")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> create(@RequestBody CreateHostRequestRO createHostRequestRO) {
        hostsService.create(createHostRequestConverter.from(createHostRequestRO));

        return ResponseEntity.ok(ResultDetailsRO.success());
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Удалить хоста", description = "Позволяет удалить хоста авторизованного в данный момент пользователя")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> delete() {
        hostsService.delete();

        return ResponseEntity.ok(ResultDetailsRO.success());
    }

    @ExceptionHandler
    private ResponseEntity<ResultDetailsRO> handleDataNotFoundException(DataNotFoundException exception) {
        ErrorDetailsRO errorDetails = new ErrorDetailsRO();
        errorDetails.setExceptionMessage(exception.getMessage());
        errorDetails.setCode(ExceptionCode.NOT_FOUND.getCode());

        ResultDetailsRO resultRO = new ResultDetailsRO();
        resultRO.setSuccess(false);
        resultRO.setError(Collections.singletonList(errorDetails));

        return ResponseEntity.ok(resultRO);
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
