package com.metaverse.files.services.user;

import java.util.Optional;

import com.metaverse.files.converters.user.UserConverter;
import com.metaverse.files.ro.user.UserRO;
import com.metaverse.files.security.models.UserModel;
import com.metaverse.files.security.repositories.UsersRepository;
import com.metaverse.files.utils.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса пользователей.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserConverter userConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNameByLogin(String login) {

        UserModel userFromDB = getUserFromDB(login);

        return userFromDB.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRO getInfoByLogin(String login) {
        UserModel userFromDB = getUserFromDB(login);

        return userConverter.to(userFromDB);
    }

    private UserModel getUserFromDB(String login) {
        Optional<UserModel> userFromDB = usersRepository.findByLogin(login);
        if (userFromDB.isEmpty()) {
            throw new DataNotFoundException(String.format("User with login %s not found", login));
        }

        return userFromDB.get();
    }
}
