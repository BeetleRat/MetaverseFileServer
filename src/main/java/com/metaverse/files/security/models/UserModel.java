package com.metaverse.files.security.models;

import java.util.ArrayList;
import java.util.List;

import com.metaverse.files.models.HostModel;
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
 * Hibernate модель представляющая объект User.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "hashed_password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_key_id", referencedColumnName = "id")
    private RegistrationKeyModel registrationKeyModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private SecurityRoleModel securityRole;

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<HostModel> hostModels;

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
     * @return имя пользователя
     */
    public String getName() {
        return name;
    }

    /**
     * @param name имя пользователя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return зашифрованный пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password зашифрованный пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return {@link RegistrationKeyModel ключ регистрации}, под которым зарегистрировался данный пользователь
     */
    public RegistrationKeyModel getRegistrationKey() {
        return registrationKeyModel;
    }

    /**
     * @param registrationKeyModel {@link RegistrationKeyModel ключ регистрации}, под которым зарегистрировался
     *                                                                          данный пользователь
     */
    public void setRegistrationKey(RegistrationKeyModel registrationKeyModel) {
        this.registrationKeyModel = registrationKeyModel;
    }

    public SecurityRoleModel getRole() {
        return securityRole;
    }

    public void setRole(SecurityRoleModel securityRoleModel) {
        this.securityRole = securityRoleModel;
    }

    /**
     * @return
     */
    public List<HostModel> getHosts() {
        return hostModels;
    }

    public void addHosts(HostModel... hostModels) {
        // Если списка не существует - создать
        if (this.hostModels == null) {
            this.hostModels = new ArrayList<>();
        }

        // Добавить все входные данные в список
        for (HostModel hostModel : hostModels) {
            // Добавляем объект в список
            this.hostModels.add(hostModel);
            // Восстанавливаем обратную связь
            hostModel.setUser(this);
        }
    }
}
