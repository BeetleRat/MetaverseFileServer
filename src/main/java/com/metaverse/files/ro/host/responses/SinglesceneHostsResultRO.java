package com.metaverse.files.ro.host.responses;

import java.util.List;

import com.metaverse.files.ro.host.HostRO;
import com.metaverse.files.ro.response.ResultDetailsRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Ответ на запрос получения хостов указанной сцены.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Schema(description = "Результат запроса хостов указанной сцены")
public class SinglesceneHostsResultRO extends ResultDetailsRO {

    @Schema(description = "Список хостов указанной сцены")
    private List<HostRO> hosts;

    /**
     * @return список хостов указанной сцены
     */
    public List<HostRO> getHosts() {
        return hosts;
    }

    /**
     * @param hosts список хостов указанной сцены
     */
    public void setHosts(List<HostRO> hosts) {
        this.hosts = hosts;
    }
}
