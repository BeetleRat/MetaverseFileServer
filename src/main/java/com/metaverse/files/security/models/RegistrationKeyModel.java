package com.metaverse.files.security.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metaverse.files.models.RoleModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Hibernate модель представляющая объект Registration Key.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Entity
@Table(name = "registration_keys")
public class RegistrationKeyModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reg_key")
    private String key;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_to")
    private Date dateTo;

    @Column(name = "organization")
    private String organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_role_id", referencedColumnName = "id")
    private SecurityRoleModel securityRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_role_id", referencedColumnName = "id")
    private RoleModel applicationRole;

    @OneToMany(mappedBy = "registrationKeyModel", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<UserModel> userModels;


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

    /**
     * @return роль пользователя на файловом сервере
     */
    public SecurityRoleModel getServerRole() {
        return securityRole;
    }

    /**
     * @param securityRoleModel роль пользователя на файловом сервере
     */
    public void setServerRole(SecurityRoleModel securityRoleModel) {
        this.securityRole = securityRoleModel;
    }

    /**
     * @return роль пользователя в приложении
     */
    public RoleModel getApplicationRole() {
        return applicationRole;
    }

    /**
     * @param applicationRole роль пользователя в приложении
     */
    public void setApplicationRole(RoleModel applicationRole) {
        this.applicationRole = applicationRole;
    }

    /**
     * @return список {@link UserModel пользователей} использовавших данный ключ регистрации
     */
    public List<UserModel> getUsers() {
        return userModels;
    }

    /**
     * Добавить пользователей использовавших данный ключ регистрации
     *
     * @param userModels {@link UserModel пользователи} использовавшие данный ключ регистрации
     */
    public void addUsers(UserModel... userModels) {
        if (this.userModels == null) {
            this.userModels = new ArrayList<>();
        }

        for (UserModel userModel : userModels) {
            this.userModels.add(userModel);
            userModel.setRegistrationKey(this);
        }
    }
}
