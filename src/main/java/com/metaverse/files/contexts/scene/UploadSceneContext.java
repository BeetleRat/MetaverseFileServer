package com.metaverse.files.contexts.scene;

import org.springframework.web.multipart.MultipartFile;

/**
 * Класс хранящий данные необходимые для загрузки на сервер новой сцены.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public class UploadSceneContext {

    private final String displayName;
    private final String device;
    private final int sortIndex;
    private final MultipartFile scene;
    private final MultipartFile image;


    private UploadSceneContext(UploadSceneContextBuilder builder) {
        this.displayName = builder.displayName;
        this.device = builder.device;
        this.sortIndex = builder.sortIndex;
        this.scene = builder.scene;
        this.image = builder.image;
    }

    /**
     * @return отображаемое имя сцены
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return устройство, для которого сделана сцена
     */
    public String getDevice() {
        return device;
    }

    /**
     * @return индекс используемый для сортировки сцен
     */
    public int getSortIndex() {
        return sortIndex;
    }

    /**
     * @return файл сцены
     */
    public MultipartFile getScene() {
        return scene;
    }

    /**
     * Есть ли в контексте файл сцены.
     *
     * @return true, если в контексте есть файл сцены
     */
    public boolean hasScene() {
        return scene != null && !scene.isEmpty();
    }

    /**
     * @return файл изображения сцены
     */
    public MultipartFile getImage() {
        return image;
    }

    /**
     * Есть ли в контексте файл изображения.
     *
     * @return true, если в контексте есть файл изображения
     */
    public boolean hasImage() {
        return image != null && !image.isEmpty();
    }

    /**
     * @return builder
     */
    public static UploadSceneContextBuilder builder() {
        return new UploadSceneContextBuilder();
    }

    /**
     * Builder.
     */
    public static class UploadSceneContextBuilder {

        private String displayName;
        private String device;
        private int sortIndex;
        private MultipartFile scene;
        private MultipartFile image;

        private UploadSceneContextBuilder() {
        }

        private UploadSceneContextBuilder self() {
            return this;
        }

        /**
         * @return отображаемое имя сцены
         */
        public UploadSceneContextBuilder displayName(String displayName) {
            this.displayName = displayName;
            return self();
        }

        /**
         * @return устройство, для которого сделана сцена
         */
        public UploadSceneContextBuilder device(String device) {
            this.device = device;
            return self();
        }

        /**
         * @return индекс используемый для сортировки сцен
         */
        public UploadSceneContextBuilder sortIndex(int sortIndex) {
            this.sortIndex = sortIndex;
            return self();
        }

        /**
         * @return файл сцены
         */
        public UploadSceneContextBuilder scene(MultipartFile scene) {
            this.scene = scene;
            return self();
        }

        /**
         * @return файл изображения сцены
         */
        public UploadSceneContextBuilder image(MultipartFile image) {
            this.image = image;
            return self();
        }

        /**
         * Собрать объект.
         *
         * @return собранный объект
         */
        public UploadSceneContext build() {
            return new UploadSceneContext(this);
        }
    }

}
