package com.metaverse.files.rest;

import com.metaverse.files.services.avatar.AvatarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST аватаров.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@RestController
@Tag(name = "Сервис аватаров", description = "Данный сервис используется работы с файлами аватаров")
@RequestMapping("/api/avatar")
public class AvatarRest {

    @Autowired
    private AvatarService avatarService;

//    @GetMapping("/getAvatarById")
//    public ResponseEntity<OperationStatusResponse> getAvatarById(LoginRO loginRO) {
////        try
////        {
////            FileStream fileStream;
////            string fileName;
////            (fileStream, fileName) =await avatarService.GetAvatarById(avatarId);
////
////            if (fileStream == null)
////            {
////                return NotFound($"file id = {avatarId} not found");
////            }
////
////            return File(fileStream, "application/octet-stream", fileName);
////        }
////        catch (FileNotFoundException ex)
////        {
////            return NotFound(ex.Message);
////        }
////        catch(Exception ex)
////        {
////            _logger.LogError(ex, "Ошибка получения файла сцены");
////            return StatusCode(500, "Internal server error");
////        }
//    }
//
//    @GetMapping("/GetAvatarByName")
//    public ResponseEntity<OperationStatusResponse> getAvatarByName(@RequestBody LoginRO loginRO) {
////        try
////        {
////            FileStream fileStream;
////            string fileName;
////            (fileStream, fileName) = await _avatarService.GetAvatarByName(queryFileName);
////
////            if (fileStream == null)
////            {
////                return NotFound($"file {queryFileName} not found");
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
//
//    @PostMapping("/UploadAvatarFile")
//    public ResponseEntity<OperationStatusResponse> uploadAvatarFile(@RequestBody LoginRO loginRO) {
////        try
////        {
////            string saveDirectory = _fileDirectories.AvatarsDirectory;
////            var avatar = await _avatarService.SaveAvatarFile(file, imageFile, saveDirectory, displayName);
////
////            if (avatar == null)
////                throw new Exception("File was not saved successfully");
////
////            return Ok(avatar);
////        }
////        catch (FileNotFoundException ex)
////        {
////            return BadRequest("Input parametres error");
////        }
////        catch (Exception ex)
////        {
////            _logger.LogError(ex, "Error uploading file");
////            return StatusCode(500, "Internal server error");
////        }
//    }
//
//    @DeleteMapping("/DeleteById")
//    public ResponseEntity<OperationStatusResponse> DeleteById(@RequestBody LoginRO loginRO) {
////        try
////        {
////            await _avatarService.DeleteAvatarById(avatarId);
////            return Ok();
////        }
////        catch (FileNotFoundException ex)
////        {
////            return BadRequest(ex.Message);
////        }
////        catch (Exception ex)
////        {
////            _logger.LogError(ex, "Error deleting file");
////            return StatusCode(500, ex.Message);
////        }
//    }
//
//    @GetMapping("/GetAllInfo")
//    public ResponseEntity<OperationStatusResponse> getAllInfo(@RequestBody LoginRO loginRO) {
////        try
////        {
////            var result = await _avatarService.GetAllInfo();
////            return Ok(result);
////        }
////        catch(Exception ex)
////        {
////            _logger.LogError(ex, "error get all avatars info");
////            return StatusCode(500, ex.Message);
////        }
//    }

}
