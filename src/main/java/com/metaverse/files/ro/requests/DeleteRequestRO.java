package com.metaverse.files.ro.requests;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object запроса на удаление.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Запрос на удаление по ID")
public class DeleteRequestRO {

    @Schema(description = "ID удаляемого объекта")
    private int id;

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }
}
