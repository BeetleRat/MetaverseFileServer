package com.metaverse.files.services.role;

import java.util.List;

import com.metaverse.files.ro.role.RoleRO;
import com.metaverse.files.utils.exceptions.UselessOperationException;

/**
 * Сервис ролей.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public interface RoleService {

    /**
     * Получить роли.
     *
     * @return список всех ролей
     */
    List<RoleRO> getAll();

    /**
     * Создать или обновить роль.
     *
     * @param roleRO роль
     */
    void update(RoleRO roleRO);

    /**
     * Удалить роль.
     *
     * @param id id удаляемой роли
     * @throws UselessOperationException если удаляемой роли нет в базе данных
     */
    void delete(int id);
}
