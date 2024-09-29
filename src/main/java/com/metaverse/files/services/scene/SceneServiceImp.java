package com.metaverse.files.services.scene;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.metaverse.files.contexts.scene.UploadSceneContext;
import com.metaverse.files.converters.scene.SceneInfoConverter;
import com.metaverse.files.models.SceneModel;
import com.metaverse.files.repositories.SceneRepository;
import com.metaverse.files.ro.scene.SceneInfoRO;
import com.metaverse.files.utils.exceptions.InvalidRequestStateException;
import com.metaverse.files.utils.exceptions.ServerFileException;
import com.metaverse.files.utils.exceptions.UselessOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация сервиса сцен.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Service
public class SceneServiceImp implements SceneService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SceneServiceImp.class);

    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    private SceneInfoConverter sceneInfoConverter;

    @Value("${application.scene.directory}")
    private String sceneDirectory;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SceneInfoRO> getAllInfo() {
        List<SceneModel> all = sceneRepository.findAll();
        return sceneInfoConverter.to(all);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScene(UploadSceneContext ctx) {
        ensureScene(ctx);
        ensureImage(ctx);

        createFilesOnDisk(ctx);

        saveSceneInfoToDB(ctx);
    }

    private static void ensureScene(UploadSceneContext ctx) {
        if (!ctx.hasScene()) {
            throw new InvalidRequestStateException("Scene file is empty");
        }
    }

    private static void ensureImage(UploadSceneContext ctx) {
        if (!ctx.hasImage()) {
            throw new InvalidRequestStateException("Image file is empty");
        }
    }

    private void createFilesOnDisk(UploadSceneContext ctx) {
        Path sceneFullName = Paths.get(sceneDirectory, ctx.getScene().getOriginalFilename());
        createFileByPath(ctx.getScene(), sceneFullName);

        Path imageFullName = Paths.get(sceneDirectory, ctx.getImage().getOriginalFilename());
        createFileByPath(ctx.getImage(), imageFullName);
    }

    private void createFileByPath(MultipartFile photoFile, Path path) {
        try {
            photoFile.transferTo(path.toFile());
        } catch (IOException e) {
            String message = String.format("File save exception: %s", e.getMessage());
            LOGGER.error(message);
            throw new ServerFileException(message);
        }
    }

    private void saveSceneInfoToDB(UploadSceneContext ctx) {
        Path sceneFullName = Paths.get(sceneDirectory, ctx.getScene().getOriginalFilename());
        Path imageFullName = Paths.get(sceneDirectory, ctx.getImage().getOriginalFilename());

        SceneModel sceneModel = new SceneModel();
        sceneModel.setDevice(ctx.getDevice());
        sceneModel.setName(ctx.getScene().getOriginalFilename());
        sceneModel.setDisplayName(ctx.getDisplayName());
        sceneModel.setFilePath(sceneFullName.toString());
        sceneModel.setImageFilePath(imageFullName.toString());
        sceneModel.setSortIndex(ctx.getSortIndex());

        sceneRepository.save(sceneModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) {

        SceneModel sceneFromDB = getSceneFromDB(id);

        deleteFilesFromDisk(sceneFromDB);

        sceneRepository.delete(sceneFromDB);
    }

    private SceneModel getSceneFromDB(int id) {
        Optional<SceneModel> sceneFromDB = sceneRepository.findById(id);
        if (sceneFromDB.isEmpty()) {
            String message = String.format("The scene with id %d does not exist", id);
            throw new UselessOperationException(message);
        }

        return sceneFromDB.get();
    }

    private static void deleteFilesFromDisk(SceneModel sceneFromDB) {
        Paths.get(sceneFromDB.getFilePath()).toFile().delete();
        Paths.get(sceneFromDB.getImageFilePath()).toFile().delete();
    }
}
