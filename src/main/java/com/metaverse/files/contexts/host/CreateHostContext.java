package com.metaverse.files.contexts.host;

/**
 * Класс хранящий данные необходимые для создания хоста.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public class CreateHostContext {

    private final String uri;
    private final String sceneName;

    private CreateHostContext(CreateHostContextBuilder builder) {
        this.uri = builder.uri;
        this.sceneName = builder.sceneName;
    }

    /**
     * @return uri идентификатор хоста
     */
    public String getUri() {
        return uri;
    }

    /**
     * @return название файла сцены
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * @return builder
     */
    public static CreateHostContextBuilder builder() {
        return new CreateHostContextBuilder();
    }

    /**
     * Builder.
     */
    public static class CreateHostContextBuilder {

        private String uri;
        private String sceneName;

        /**
         * @param uri uri идентификатор хоста
         * @return self
         */
        public CreateHostContextBuilder uri(String uri) {
            this.uri = uri;
            return this;
        }

        /**
         * @param sceneName название файла сцены
         * @return self
         */
        public CreateHostContextBuilder sceneName(String sceneName) {
            this.sceneName = sceneName;
            return this;
        }

        /**
         * Собрать объект.
         *
         * @return собранный объект
         */
        public CreateHostContext build() {
            return new CreateHostContext(this);
        }
    }
}
