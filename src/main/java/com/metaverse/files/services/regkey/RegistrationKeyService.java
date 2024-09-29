package com.metaverse.files.services.regkey;

import java.util.List;

import com.metaverse.files.ro.regkey.RegistrationKeyRO;
import com.metaverse.files.utils.exceptions.InvalidRequestStateException;
import com.metaverse.files.utils.exceptions.UselessOperationException;

/**
 * Сервис ключей регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public interface RegistrationKeyService {

    /**
     * Получить все ключи регистрации.
     *
     * @return список всех ключей регистрации
     */
    List<RegistrationKeyRO> getAll();

    /**
     * Создать ключ регистрации.
     *
     * @param regKey ключ регистрации
     * @throws UselessOperationException    если создаваемый ключ уже существует
     * @throws InvalidRequestStateException если создаваемый ключ содержит не валидные значения
     */
    void create(RegistrationKeyRO regKey);

    /**
     * Удалить ключ регистрации.
     *
     * @param id id удаляемого ключа
     * @throws UselessOperationException если удаляемого ключа нет в базе данных
     */
    void delete(int id);
}
