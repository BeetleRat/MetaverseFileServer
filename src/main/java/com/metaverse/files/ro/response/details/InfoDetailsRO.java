package com.metaverse.files.ro.response.details;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object информирующего поля.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Поле содержащие информацию")
public class InfoDetailsRO {

    @Schema(description = "Информация в строковом представлении")
    private String content;

    /**
     * @return информация в строковом представлении
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content информация в строковом представлении
     */
    public void setContent(String content) {
        this.content = content;
    }
}
