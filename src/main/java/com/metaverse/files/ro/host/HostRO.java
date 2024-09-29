package com.metaverse.files.ro.host;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object хоста.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Объект хоста")
public class HostRO {

    @Schema(description = "Uri идентификатор хоста, для подключения к нему через Mirror")
    private String uri;
    @Schema(description = "Название файла сцены, хостом которой является создаваемый хост")
    private String sceneName;
    @Schema(description = "Логин пользователя являющегося хостом")
    private String login;

    /**
     * @return uri идентификатор хоста
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri uri идентификатор хоста
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return название файла сцены
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * @param sceneName название файла сцены
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    /**
     * @return логин пользователя являющегося хостом
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login логин пользователя являющегося хостом
     */
    public void setLogin(String login) {
        this.login = login;
    }
}
