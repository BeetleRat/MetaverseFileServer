package com.metaverse.files.security.jwt;

import java.io.IOException;
import java.util.List;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Фильтр добавляющий логику обработки JSON Web Token в Security chain.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Value("${application.jwt.authHeader}")
    private String authorizationHeaderName = "";
    @Value("${application.jwt.tokenPrefix}")
    private String authorizationPrefix = "";

    @Autowired
    private JwtConfig jwtConfig;

    private SecurityContext securityContext;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        securityContext = SecurityContextHolder.getContext();

        scanJWT(request);

        // Передаем запрос следующему фильтру в цепочке
        filterChain.doFilter(request, response);
    }

    private void scanJWT(HttpServletRequest request) {
        String authorization =
                request.getHeader(authorizationHeaderName);

        if (authorization != null
                && authorization.startsWith(authorizationPrefix)) {
            encodeHeader(authorization);
        }
    }

    private void encodeHeader(String authorizationHeaderWithPrefix) {
        String jwt = authorizationHeaderWithPrefix.substring(authorizationPrefix.length());
        String username = getUsernameFromToken(jwt);

        if (!username.equals("")
                && securityContext.getAuthentication() == null) {

            addUserAndPermissionsToSecurityContext(
                    username,
                    jwtConfig.getUserPermissionsAsSGA(jwt)
            );
        }
    }

    private void addUserAndPermissionsToSecurityContext(String username,
                                                        List<SimpleGrantedAuthority> permissions) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, permissions);

        securityContext.setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String getUsernameFromToken(String jwt) {
        String username = "";

        try {
            username = jwtConfig.getUsername(jwt);
        } catch (ExpiredJwtException e) {
            LOGGER.warn("Время жизни токена вышло");
        } catch (SignatureException e) {
            LOGGER.warn("Подпись не корректна");
        }

        return username;
    }
}
