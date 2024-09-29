package com.metaverse.files.utils.exceptions;

/**
 * Операция не имеет смысла, {@literal т.к.} ее результат уже достигнут.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public class UselessOperationException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message сообщение об ошибке
     */
    public UselessOperationException(final String message) {
        super(message);
    }
}
