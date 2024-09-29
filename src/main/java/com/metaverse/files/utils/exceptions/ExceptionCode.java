package com.metaverse.files.utils.exceptions;

/**
 * Коды ошибок.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public enum ExceptionCode {
    BAD_REQUEST("400"),
    UNAUTHORIZED("401"),
    NOT_FOUND("404"),
    INTERNAL_SERVER_ERROR("500");

    private final String code;

    /**
     * Конструктор.
     *
     * @param code строковое представление кода ошибки
     */
    ExceptionCode(String code) {
        this.code = code;
    }

    /**
     * @return строковое представление кода ошибки
     */
    public String getCode() {
        return code;
    }
}
