package com.metaverse.files.utils.exceptions;

/**
 * Ошибка - данные не найдены в базе данных.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public class DataNotFoundException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message сообщение об ошибке
     */
    public DataNotFoundException(final String message) {
        super(message);
    }
}
