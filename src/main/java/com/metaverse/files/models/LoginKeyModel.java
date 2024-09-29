package com.metaverse.files.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Hibernate модель представляющая объект Login Key.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Entity
@Table(name = "login_keys")
public class LoginKeyModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login_key")
    private String key;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_to")
    private Date dateTo;

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return ключ входа
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key ключ входа
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return дата начала актуальности ключа
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom дата начала актуальности ключа
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return дата окончания актуальности ключа
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo дата окончания актуальности ключа
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
