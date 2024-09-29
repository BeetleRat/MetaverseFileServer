package com.metaverse.files.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Класс конфигурации JSON Web Token.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Configuration
public class JwtConfig {

    private final static String PERMISSIONS = "permissions";

    // Поля конфигурируются через свойства указанные в файле application.properties
    @Value("${application.jwt.secretKey}")
    private String secretKey;
    @Value("${application.jwt.tokenPrefix}")
    private String tokenPrefix;
    @Value("${application.jwt.tokenExpirationAfterDays}")
    private Integer tokenExpirationAfterDays;
    @Value("${application.jwt.authHeader}")
    private String authHeader;

    // Обязательно должен быть конструктор по умолчанию
    public JwtConfig() {
    }

    /**
     * Генерирует JSON Web Token на основе данных безопасности о пользователе.
     *
     * @param userDetails данные безопасности о пользователе
     * @return JSON Web Token
     */
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = getClaimsFromUser(userDetails);

        Date creationDate = new Date();
        Date expiredDate = new Date(creationDate.getTime() + TimeUnit.DAYS.toMillis(tokenExpirationAfterDays));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(creationDate)
                .setExpiration(expiredDate)
                .signWith(getRealSecretKey())
                .compact();
    }

    private Map<String, Object> getClaimsFromUser(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // Получаем список разрешений пользователя
        List<String> permissionsList =
                userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

        claims.put(PERMISSIONS, permissionsList);

        return claims;
    }

    /**
     * Получить секретный ключ для генерации токена основываясь на secretKey.
     *
     * @return секретный ключ для генерации токена
     */
    public SecretKey getRealSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Получить имя пользователя из токена.
     *
     * @param token JSON Web Token
     * @return имя пользователя
     */
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * Получает разрешения пользователя из токена в строковом представлении.
     *
     * @param token JSON Web Token
     * @return список разрешений пользователя в строковом представлении
     */
    public List<String> getUserPermissionsAsString(String token) {
        return getAllClaimsFromToken(token).get(PERMISSIONS, List.class);
    }

    /**
     * Получает разрешения пользователя из токена в виде {@link SimpleGrantedAuthority}.
     *
     * @param token JSON Web Token
     * @return список разрешений пользователя в виде {@link SimpleGrantedAuthority}
     */
    public List<SimpleGrantedAuthority> getUserPermissionsAsSGA(String token) {
        return getUserPermissionsAsString(token).stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getRealSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
