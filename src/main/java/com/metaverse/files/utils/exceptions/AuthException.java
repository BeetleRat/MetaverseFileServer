package com.metaverse.files.utils.exceptions;

/**
 * Ошибка авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public class AuthException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message сообщение об ошибке
     */
    public AuthException(final String message) {
        super(message);
    }
}
