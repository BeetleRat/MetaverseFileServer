package com.metaverse.files.ro.host.responses;

import java.util.List;
import java.util.Map;

import com.metaverse.files.ro.host.HostRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Ответ на запрос получения всех существующих хостов.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Результат запроса множества существующих хостов")
public class MultisceneHostsResultRO extends ResultDetailsRO {

    /**
     * {@literal Map<RoomName, HostRO>}
     */
    @Schema(description = "Множество хостов сгруппированных по имени комнаты. Dictionary<RoomName, HostRO>")
    private Map<String, List<HostRO>> hosts;

    /**
     * @return хосты {@literal Map<RoomName, HostRO>}
     */
    public Map<String, List<HostRO>> getHosts() {
        return hosts;
    }

    /**
     * @param hosts хосты {@literal Map<RoomName, HostRO>}
     */
    public void setHosts(Map<String, List<HostRO>> hosts) {
        this.hosts = hosts;
    }
}
