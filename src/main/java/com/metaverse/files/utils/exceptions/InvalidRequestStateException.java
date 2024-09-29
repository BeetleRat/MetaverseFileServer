package com.metaverse.files.utils.exceptions;

/**
 * Не валидное состояние запроса.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public class InvalidRequestStateException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message сообщение об ошибке
     */
    public InvalidRequestStateException(final String message) {
        super(message);
    }
}
