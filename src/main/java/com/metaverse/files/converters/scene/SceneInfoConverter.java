package com.metaverse.files.converters.scene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.metaverse.files.converters.Converter;
import com.metaverse.files.models.SceneModel;
import com.metaverse.files.ro.scene.SceneInfoRO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Конвертер SceneModel в SceneInfoRO.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class SceneInfoConverter extends Converter<SceneModel, SceneInfoRO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SceneInfoConverter.class);

    /**
     * Конструктор.
     */
    public SceneInfoConverter() {
        super();
        this.from = null;
        this.to = this::convert;
    }

    private SceneInfoRO convert(final SceneModel model) {
        SceneInfoRO ro = new SceneInfoRO();

        ro.setSortIndex(model.getSortIndex());
        ro.setName(model.getName());
        ro.setDisplayName(model.getDisplayName());
        ro.setDevice(model.getDevice());
        ro.setImageFilePath(model.getImageFilePath());

        Path imagePath = Paths.get(model.getImageFilePath()).normalize();
        ro.setImageData(getBytesFromFile(imagePath.toFile()));

        return ro;
    }

    @Nullable
    private byte[] getBytesFromFile(File imageFile) {
        if (!imageFile.exists()) {
            LOGGER.error(String.format("File %s not found", imageFile.getPath()));
            return null;
        }

        try {
            return Files.readAllBytes(Path.of(imageFile.getPath()));
        } catch (IOException e) {
            LOGGER.error(String.format("File %s can not read", imageFile.getPath()));
            return null;
        }
    }
}
