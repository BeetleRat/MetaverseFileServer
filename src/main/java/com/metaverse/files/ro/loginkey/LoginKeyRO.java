package com.metaverse.files.ro.loginkey;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object ключа авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Объект ключа авторизации")
public class LoginKeyRO {

    @Schema(description = "Ключ авторизации")
    private String key;
    @Schema(description = "Дата начала периода актуальности ключа")
    private Date dateFrom;
    @Schema(description = "Дата окончания периода актуальности ключа")
    private Date dateTo;

    /**
     * @return ключ авторизации
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key ключ авторизации
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
}
