package com.metaverse.files.rest;

import java.util.Collections;
import java.util.List;

import com.metaverse.files.contexts.scene.UploadSceneContext;
import com.metaverse.files.ro.regkey.responses.RegistrationKeysResultRO;
import com.metaverse.files.ro.requests.DeleteRequestRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import com.metaverse.files.ro.response.details.ErrorDetailsRO;
import com.metaverse.files.ro.response.details.InfoDetailsRO;
import com.metaverse.files.ro.scene.SceneInfoRO;
import com.metaverse.files.services.scene.SceneService;
import com.metaverse.files.utils.exceptions.ExceptionCode;
import com.metaverse.files.utils.exceptions.InvalidRequestStateException;
import com.metaverse.files.utils.exceptions.ServerFileException;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * REST сцен.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@RestController
@Tag(name = "Сервис сцен", description = "Данный сервис используется для работы с файлами сцен")
@RequestMapping("/api/scenes")
public class ScenesRest {

    @Autowired
    private SceneService sceneService;

//    @GetMapping("/GetSceneById")
//    public ResponseEntity<OperationStatusResponse> getSceneById(int sceneId) {
////        try
////        {
////            FileStream fileStream;
////            string fileName;
////            (fileStream, fileName) = await _sceneService.GetSceneById(sceneId);
////
////            if (fileStream == null)
////            {
////                return NotFound($"File id = {sceneId} not found");
////            }
////
////            return File(fileStream, "application/octet-stream", fileName);
////        }
////        catch(FileNotFoundException ex)
////        {
////            return NotFound(ex.Message);
////        }
////        catch (Exception ex)
////        {
////            _logger.LogError(ex, "Ошибка получения файла сцены");
////            return StatusCode(500, "Internal server error");
////        }
//    }

//    @GetMapping("/GetSceneByName")
//    public ResponseEntity<OperationStatusResponse> getSceneByName(String queryFileName) {
////        try
////        {
////            FileStream fileStream;
////            string fileName;
////            (fileStream, fileName) = await _sceneService.GetSceneByName(queryFileName);
////
////            if (fileStream == null)
////            {
////                return NotFound($"File name = {queryFileName} not found");
////            }
////
////            return File(fileStream, "application/octet-stream", fileName);
////        }
////        catch (FileNotFoundException ex)
////        {
////            return NotFound(ex.Message);
////        }
////        catch (Exception ex)
////        {
////            _logger.LogError(ex, "Ошибка получения файла сцены");
////            return StatusCode(500, "Internal server error");
////        }
//    }

    @GetMapping("/all-info")
    @Operation(summary = "Получить список с информацией о сценах", description = "Позволяет список содержащий информацию о всех сценах файлового сервера")
    @ApiResponse(responseCode = "200", description = "Список список с информацией о всех сценах", content = @Content(schema = @Schema(implementation = RegistrationKeysResultRO.class)))
    public ResponseEntity<List<SceneInfoRO>> getAllInfo() {
        List<SceneInfoRO> scenes = sceneService.getAllInfo();
        return ResponseEntity.ok(scenes);
    }

    @PostMapping(
            path = "/upload-scene",
            consumes = {
                    "multipart/form-data"
            }
    )
    @Operation(summary = "Загрузить сцену на сервер", description = "Позволяет загрузить файл сцены на файловый сервер")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> uploadSceneFile(@RequestParam("scene") MultipartFile scene,
                                                           @RequestParam("img") MultipartFile img,
                                                           @RequestParam("displayName") String displayName,
                                                           @RequestParam("device") String device,
                                                           @RequestParam("sortIndex") int sortIndex) {
        UploadSceneContext ctx = UploadSceneContext.builder()
                .image(img)
                .scene(scene)
                .displayName(displayName)
                .device(device)
                .sortIndex(sortIndex)
                .build();

        sceneService.saveScene(ctx);

        return ResponseEntity.ok(ResultDetailsRO.success());
    }


    @DeleteMapping("/delete")
    @Operation(summary = "Удалить сцену", description = "Позволяет удалить файл сцены с указанным id с файлового сервера")
    @ApiResponse(responseCode = "200", description = "Статус выполнения запроса", content = @Content(schema = @Schema(implementation = ResultDetailsRO.class)))
    public ResponseEntity<ResultDetailsRO> delete(@RequestBody DeleteRequestRO deleteRequestRO) {
        sceneService.delete(deleteRequestRO.getId());

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

    @ExceptionHandler
    private ResponseEntity<ResultDetailsRO> handleServerFileException(ServerFileException exception) {
        ErrorDetailsRO error = new ErrorDetailsRO();
        error.setExceptionMessage(exception.getMessage());
        error.setCode(ExceptionCode.INTERNAL_SERVER_ERROR.getCode());

        ResultDetailsRO resultRO = new ResultDetailsRO();
        resultRO.setSuccess(false);
        resultRO.setError(Collections.singletonList(error));

        return ResponseEntity.ok(resultRO);
    }
}
