package com.metaverse.files.ro.response.details;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object поля ошибки.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Поле содержащие информацию об ошибке")
public class ErrorDetailsRO {

    @Schema(description = "Код ошибки")
    private String code;
    @Schema(description = "Сообщение об ошибке")
    private String exceptionMessage;

    /**
     * @return код ошибки
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code код ошибки
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return сообщение об ошибке
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    /**
     * @param exceptionMessage сообщение об ошибке
     */
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
