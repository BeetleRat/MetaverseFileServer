package com.metaverse.files.services.host;

import java.util.List;
import java.util.Map;

import com.metaverse.files.contexts.host.CreateHostContext;
import com.metaverse.files.ro.host.HostRO;
import com.metaverse.files.utils.exceptions.DataNotFoundException;
import com.metaverse.files.utils.exceptions.UselessOperationException;

/**
 * Сервис хостов.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public interface HostsService {

    /**
     * Получить всех хостов сгруппированных по имени сцены.
     *
     * @return множество хостов сгруппированных по имени комнаты. {@literal Map<RoomName, HostRO>}
     */
    Map<String, List<HostRO>> hostsGroupedByScene();

    /**
     * Получить список хостов указанной сцены.
     *
     * @param sceneName имя файла сцены
     * @return список хостов указанной сцены
     */
    List<HostRO> hostsByScene(String sceneName);

    /**
     * Создать хоста из авторизованного пользователя.
     *
     * @param ctx {@link CreateHostContext контекст создания хоста}
     * @throws DataNotFoundException     если не удалось найти в БД данные для создания хоста
     * @throws UselessOperationException если данный пользователь уже является хостом
     */
    void create(CreateHostContext ctx);

    /**
     * Удалить хоста для авторизованного в данный момент пользователя.
     *
     * @throws UselessOperationException если данный пользователь не является хостом
     */
    void delete();
}
