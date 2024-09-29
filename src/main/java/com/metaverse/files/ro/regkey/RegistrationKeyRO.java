package com.metaverse.files.ro.regkey;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object ключа регистрации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Объект ключа регистрации")
public class RegistrationKeyRO {

    @Schema(description = "Ключ регистрации")
    private String key;
    @Schema(description = "Дата начала периода актуальности ключа")
    private Date dateFrom;
    @Schema(description = "Дата окончания периода актуальности ключа")
    private Date dateTo;
    @Schema(description = "ID роли на файловом сервере")
    private int serverRoleId;
    @Schema(description = "ID роли в приложении")
    private int applicationRoleId;
    @Schema(description = "Организация создавшая ключ")
    private String organization;

    /**
     * @return ключ регистрации
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key ключ регистрации
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return дата начала периода актуальности ключа
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom дата начала периода актуальности ключа
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return дата окончания периода актуальности ключа
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo дата окончания периода актуальности ключа
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return id роли на файловом сервере
     */
    public int getServerRoleId() {
        return serverRoleId;
    }

    /**
     * @param serverRoleId id роли на файловом сервере
     */
    public void setServerRoleId(int serverRoleId) {
        this.serverRoleId = serverRoleId;
    }

    /**
     * @return id роли в приложении
     */
    public int getApplicationRoleId() {
        return applicationRoleId;
    }

    /**
     * @param applicationRoleId id роли в приложении
     */
    public void setApplicationRoleId(int applicationRoleId) {
        this.applicationRoleId = applicationRoleId;
    }

    /**
     * @return организация создавшая ключ
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization организация создавшая ключ
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
