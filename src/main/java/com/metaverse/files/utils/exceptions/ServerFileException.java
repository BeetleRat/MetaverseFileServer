package com.metaverse.files.utils.exceptions;

/**
 * Ошибка работы с файлами сервера.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public class ServerFileException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message сообщение об ошибке
     */
    public ServerFileException(final String message) {
        super(message);
    }
}
