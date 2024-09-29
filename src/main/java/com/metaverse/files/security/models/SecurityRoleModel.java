package com.metaverse.files.security.models;

import java.util.ArrayList;
import java.util.List;

import com.metaverse.files.security.types.UserServerRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Hibernate модель представляющая объект роль на сервере.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Entity
@Table(name = "server_role")
public class SecurityRoleModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private UserServerRole role;

    @Column(name = "full_access")
    private boolean fullAccess;

    @OneToMany(mappedBy = "securityRole", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<RegistrationKeyModel> registrationKeyModels;

    @OneToMany(mappedBy = "securityRole", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
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
     * @return {@link UserServerRole роль на сервере}
     */
    public UserServerRole getRole() {
        return role;
    }

    /**
     * @param role {@link UserServerRole роль на сервере}
     */
    public void setRole(UserServerRole role) {
        this.role = role;
    }

    /**
     * @return true, если у данной роли есть полный доступ
     */
    public boolean hasFullAccess() {
        return fullAccess;
    }

    /**
     * @param fullAccess есть ли у данной роли полный доступ
     */
    public void setFullAccess(boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    /**
     * @return {@link RegistrationKeyModel ключи регистрации}, которые регистрировали пользователей с данной ролью
     */
    public List<RegistrationKeyModel> getRegistrationKeys() {
        return registrationKeyModels;
    }

    /**
     * Добавить {@link RegistrationKeyModel ключи регистрации}, которые регистрировали пользователей с данной ролью
     *
     * @param keys {@link RegistrationKeyModel ключи регистрации}, которые регистрировали пользователей с данной ролью
     */
    public void addRegistrationKeys(RegistrationKeyModel... keys) {
        if (this.registrationKeyModels == null) {
            this.registrationKeyModels = new ArrayList<>();
        }

        for (RegistrationKeyModel key : keys) {
            this.registrationKeyModels.add(key);
            key.setServerRole(this);
        }
    }

    /**
     * @return {@link UserModel пользователи} обладающие данной ролью
     */
    public List<UserModel> getUsers() {
        return userModels;
    }

    /**
     * Добавить {@link UserModel пользователей} обладающих данной ролью
     *
     * @param userModels {@link UserModel пользователи} обладающие данной ролью
     */
    public void addUsers(UserModel... userModels) {
        if (this.userModels == null) {
            this.userModels = new ArrayList<>();
        }

        for (UserModel userModel : userModels) {
            this.userModels.add(userModel);
            userModel.setRole(this);
        }
    }
}
