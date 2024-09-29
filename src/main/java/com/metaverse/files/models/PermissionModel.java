package com.metaverse.files.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Hibernate модель представляющая объект Permission.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Entity
@Table(name = "application_permissions")
public class PermissionModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<RoleModel> roles;

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
     * @return название разрешения
     */
    public String getName() {
        return name;
    }

    /**
     * @param name название разрешения
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link RoleModel роли}, у которых есть данное разрешение
     */
    public Set<RoleModel> getRoles() {
        return roles;
    }

    /**
     * Добавить {@link RoleModel роли}, у которых есть данное разрешение.
     *
     * @param roles {@link RoleModel роли}, у которых есть данное разрешение
     */
    public void addRole(RoleModel... roles) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }

        for (RoleModel role : roles) {
            if (this.roles.add(role)) {
                role.addPermission(this);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionModel that = (PermissionModel) o;
        return id == that.id && Objects.equals(name, that.name);
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
