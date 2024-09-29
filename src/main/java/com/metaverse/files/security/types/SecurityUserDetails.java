package com.metaverse.files.security.types;

import java.util.Collection;
import java.util.Set;

import com.metaverse.files.security.models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Класс пользователя spring security.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
public class SecurityUserDetails implements UserDetails {

    /**
     * Получить пользователя авторизованного в данный момент.
     *
     * @return spring security пользователь, авторизованный в данный момент
     */
    public static SecurityUserDetails CurrentLoginUser() {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                || !(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SecurityUserDetails)) {
            return null;
        }

        return (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private UserModel securityUser;
    /**
     * Права (разрешения) пользователя
     */
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    /**
     * Конструктор.
     *
     * @param securityUser spring security пользователь
     */
    public SecurityUserDetails(UserModel securityUser) {
        this.securityUser = securityUser;
        this.grantedAuthorities = this.securityUser.getRole().getRole().getGrantedAuthority();
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return this.securityUser.getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.securityUser.getLogin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Получить {@link UserModel модель пользователя}.
     *
     * @return {@link UserModel модель пользователя}
     */
    public UserModel getSecurityUser() {
        return securityUser;
    }
}
