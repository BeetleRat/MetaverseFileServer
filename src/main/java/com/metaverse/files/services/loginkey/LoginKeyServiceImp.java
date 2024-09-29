package com.metaverse.files.services.loginkey;

import java.util.List;
import java.util.Optional;

import com.metaverse.files.converters.loginkey.LoginKeyConverter;
import com.metaverse.files.models.LoginKeyModel;
import com.metaverse.files.repositories.LoginKeyRepository;
import com.metaverse.files.ro.loginkey.LoginKeyRO;
import com.metaverse.files.utils.exceptions.UselessOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса ключей авторизации.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Service
public class LoginKeyServiceImp implements LoginKeyService {

    @Autowired
    private LoginKeyRepository loginKeyRepository;
    @Autowired
    private LoginKeyConverter loginKeyConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LoginKeyRO> getAll() {
        List<LoginKeyModel> keysFromDB = loginKeyRepository.findAll();
        return loginKeyConverter.to(keysFromDB);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(LoginKeyRO loginKeyRO) {
        ensureKey(loginKeyRO);
        saveKey(loginKeyRO);
    }

    private void ensureKey(LoginKeyRO loginKeyRO) {
        Optional<LoginKeyModel> keyFromDB = loginKeyRepository.findByKey(loginKeyRO.getKey());
        if (keyFromDB.isPresent()) {
            throw new UselessOperationException("The key already exists");
        }
    }

    private void saveKey(LoginKeyRO loginKeyRO) {
        LoginKeyModel keyModel = new LoginKeyModel();
        keyModel.setKey(loginKeyRO.getKey());
        keyModel.setDateFrom(loginKeyRO.getDateFrom());
        keyModel.setDateTo(loginKeyRO.getDateTo());

        loginKeyRepository.save(keyModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) {
        LoginKeyModel keyFromDB = getKeyFromDB(id);
        loginKeyRepository.delete(keyFromDB);
    }

    private LoginKeyModel getKeyFromDB(int id) {
        Optional<LoginKeyModel> keyFromDB = loginKeyRepository.findById(id);
        if (keyFromDB.isEmpty()) {
            final String message = String.format("The key with id %d does not exist", id);
            throw new UselessOperationException(message);
        }

        return keyFromDB.get();
    }
}
