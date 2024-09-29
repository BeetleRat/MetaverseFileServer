package com.metaverse.files.services.auth;

import java.util.Date;
import java.util.Optional;

import com.metaverse.files.contexts.auth.LoginContext;
import com.metaverse.files.contexts.auth.RegistrationContext;
import com.metaverse.files.models.LoginKeyModel;
import com.metaverse.files.repositories.LoginKeyRepository;
import com.metaverse.files.repositories.RegistrationKeyRepository;
import com.metaverse.files.security.jwt.JwtConfig;
import com.metaverse.files.security.models.RegistrationKeyModel;
import com.metaverse.files.security.models.UserModel;
import com.metaverse.files.security.repositories.UsersRepository;
import com.metaverse.files.security.types.SecurityUserDetails;
import com.metaverse.files.utils.StringUtils;
import com.metaverse.files.utils.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RegistrationKeyRepository regKeyRepository;
    @Autowired
    private LoginKeyRepository loginKeyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtConfig jwt;

    /**
     * {@inheritDoc}
     */
    @Override
    public String login(LoginContext ctx) {
        UserModel user = getUserFromDB(ctx);

        if (!user.getRole().hasFullAccess()) {
            ensureLoginKey(ctx);
        }

        ensurePassword(ctx, user);

        return jwt.generateToken(new SecurityUserDetails(user));
    }

    private UserModel getUserFromDB(LoginContext ctx) {
        String login = ctx.getLogin();
        Optional<UserModel> userFromDB = usersRepository.findByLogin(login);

        if (userFromDB.isEmpty()) {
            throw new AuthException(String.format("User %s not found", login));
        }

        return userFromDB.get();
    }

    private void ensureLoginKey(LoginContext ctx) {
        if (ctx.getLoginKey() == null) {
            throw new AuthException("Login key is required");
        }

        Optional<LoginKeyModel> keyFromDB = loginKeyRepository.findByKey(ctx.getLoginKey());
        if (keyFromDB.isEmpty()) {
            throw new AuthException("Login key not exist");
        }

        ensureValidityRage(keyFromDB.get());
    }

    private static void ensureValidityRage(LoginKeyModel regKey) {
        Date now = new Date();
        if (regKey.getDateFrom().after(now)) {
            throw new AuthException("The key's validity period has not yet begun");
        }
        if (regKey.getDateTo().before(now)) {
            throw new AuthException("The key has expired");
        }
    }

    private void ensurePassword(LoginContext ctx, UserModel userModel) {
        boolean passwordCorrect = passwordEncoder.matches(ctx.getPassword(), userModel.getPassword());
        if (!passwordCorrect) {
            throw new AuthException("Wrong password");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String registration(RegistrationContext ctx) {
        ensureName(ctx);

        ensureLogin(ctx);

        ensurePassword(ctx);

        RegistrationKeyModel regKey = getRegistrationKeyFromDB(ctx);

        UserModel userModel = saveUser(ctx, regKey);

        return jwt.generateToken(new SecurityUserDetails(userModel));
    }

    private static void ensureName(RegistrationContext ctx) {
        if (StringUtils.isEmpty(ctx.getName())) {
            throw new AuthException("Name must not be empty");
        }
    }

    private void ensureLogin(RegistrationContext ctx) {
        String login = ctx.getLogin();
        if (StringUtils.isEmpty(login)) {
            throw new AuthException("Login must not be empty");
        }

        Optional<UserModel> userFromDB = usersRepository.findByLogin(login);
        if (userFromDB.isPresent()) {
            throw new AuthException("User with this login already exists");
        }
    }

    private static void ensurePassword(RegistrationContext ctx) {
        if (ctx.passwordsNotMatch()) {
            throw new AuthException("Entered passwords do not match");
        }

        if (ctx.getPassword().length() < 6) {
            throw new AuthException("The password must not be less than 6 characters");
        }
    }

    private RegistrationKeyModel getRegistrationKeyFromDB(RegistrationContext ctx) {
        Optional<RegistrationKeyModel> keyFromDB = regKeyRepository.findByKey(ctx.getRegistrationKey());
        if (keyFromDB.isEmpty()) {
            throw new AuthException("Incorrect registration key");
        }

        RegistrationKeyModel regKey = keyFromDB.get();

        ensureValidityRange(regKey);

        return regKey;
    }

    private static void ensureValidityRange(RegistrationKeyModel regKey) {
        Date now = new Date();
        if (regKey.getDateFrom().after(now)) {
            throw new AuthException("The registration key's validity period has not yet begun");
        }
        if (regKey.getDateTo().before(now)) {
            throw new AuthException("The registration key has expired");
        }
    }

    private UserModel saveUser(RegistrationContext ctx, RegistrationKeyModel regKey) {
        UserModel userModel = new UserModel();
        userModel.setName(ctx.getName());
        userModel.setLogin(ctx.getLogin());
        userModel.setPassword(passwordEncoder.encode(ctx.getPassword()));
        userModel.setRegistrationKey(regKey);
        userModel.setRole(regKey.getServerRole());

        usersRepository.save(userModel);

        return userModel;
    }
}
