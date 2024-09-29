package com.metaverse.files.ro.scene;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object сцены.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Объект сцены")
public class SceneInfoRO {

    @Schema(description = "Индекс сортировки сцены")
    private int sortIndex;
    @Schema(description = "Имя файла сцены")
    private String name;
    @Schema(description = "Отображаемое имя сцены")
    private String displayName;
    @Schema(description = "Устройство, для которого сделана сцена")
    private String device;
    @Schema(description = "Путь до файла изображения сцены на сервере")
    private String imageFilePath;
    @Schema(description = "Изображение сцены")
    private byte[] imageData;

    /**
     * @return индекс сортировки сцены
     */
    public int getSortIndex() {
        return sortIndex;
    }

    /**
     * @param sortIndex индекс сортировки сцены
     */
    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    /**
     * @return имя файла сцены
     */
    public String getName() {
        return name;
    }

    /**
     * @param name имя файла сцены
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return отображаемое имя сцены
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName отображаемое имя сцены
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return устройство, для которого сделана сцена
     */
    public String getDevice() {
        return device;
    }

    /**
     * @param device устройство, для которого сделана сцена
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * @return путь до файла изображения сцены на сервере
     */
    public String getImageFilePath() {
        return imageFilePath;
    }

    /**
     * @param imageFilePath путь до файла изображения сцены на сервере
     */
    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    /**
     * @return изображение сцены
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * @param imageData изображение сцены
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
