package com.metaverse.files.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.metaverse.files.security.models.RegistrationKeyModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Hibernate модель представляющая объект Role.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Entity
@Table(name = "application_role")
public class RoleModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "application_role_permissions",
            joinColumns = @JoinColumn(name = "application_role_id"),
            inverseJoinColumns = @JoinColumn(name = "application_permission_id")
    )
    private Set<PermissionModel> permissions;

    @OneToMany(mappedBy = "applicationRole", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<RegistrationKeyModel> registrationKeyModels;

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
     * @return название роли
     */
    public String getName() {
        return name;
    }

    /**
     * @param name название роли
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link PermissionModel права (разрешения)} роли
     */
    public Set<PermissionModel> getPermissions() {
        return permissions;
    }

    /**
     * Добавить {@link PermissionModel права (разрешения)} к роли.
     *
     * @param permissions {@link PermissionModel права (разрешения)}
     */
    public void addPermission(PermissionModel... permissions) {
        if (this.permissions == null) {
            this.permissions = new HashSet<>();
        }

        for (PermissionModel permission : permissions) {
            if (this.permissions.add(permission)) {
                permission.addRole(this);
            }
        }
    }

    /**
     * @return {@link RegistrationKeyModel ключи регистрации}, которые зарегистрировали пользователей с данной ролью
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
            key.setApplicationRole(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleModel roleModel = (RoleModel) o;
        return id == roleModel.id && Objects.equals(name, roleModel.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(name);
        return result;
    }
}
