package com.metaverse.files.services.loginkey;

import java.util.List;

import com.metaverse.files.ro.loginkey.LoginKeyRO;
import com.metaverse.files.utils.exceptions.UselessOperationException;

/**
 * Сервис ключей авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public interface LoginKeyService {

    /**
     * Получить все ключи авторизации.
     *
     * @return список всех ключей авторизации
     */
    List<LoginKeyRO> getAll();

    /**
     * Создать ключ авторизации.
     *
     * @param loginKeyRO ключ авторизации
     * @throws UselessOperationException если создаваемый ключ уже существует
     */
    void create(LoginKeyRO loginKeyRO);

    /**
     * Удалить ключ авторизации.
     *
     * @param id id удаляемого ключа
     * @throws UselessOperationException если удаляемого ключа нет в базе данных
     */
    void delete(int id);
}
