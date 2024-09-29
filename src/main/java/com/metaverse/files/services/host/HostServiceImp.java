package com.metaverse.files.services.host;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.metaverse.files.contexts.host.CreateHostContext;
import com.metaverse.files.converters.host.HostConverter;
import com.metaverse.files.models.HostModel;
import com.metaverse.files.models.SceneModel;
import com.metaverse.files.repositories.HostsRepository;
import com.metaverse.files.repositories.SceneRepository;
import com.metaverse.files.ro.host.HostRO;
import com.metaverse.files.security.models.UserModel;
import com.metaverse.files.security.repositories.UsersRepository;
import com.metaverse.files.utils.exceptions.DataNotFoundException;
import com.metaverse.files.utils.exceptions.UselessOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса хостов.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Service
public class HostServiceImp implements HostsService {

    @Autowired
    private HostsRepository hostsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    private HostConverter hostConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<HostRO>> hostsGroupedByScene() {
        List<HostModel> hosts = hostsRepository.findAll();
        return hosts.stream()
                .collect(
                        Collectors.groupingBy(
                                e -> e.getScene().getName(),
                                Collectors.mapping(
                                        host -> hostConverter.to(host),
                                        Collectors.toList()
                                )
                        )
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HostRO> hostsByScene(String sceneName) {
        List<HostModel> hosts = hostsRepository.findAllBySceneModelName(sceneName);
        return hostConverter.to(hosts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CreateHostContext ctx) {
        UserModel userModel = getCurrentUser();
        SceneModel sceneFromDB = getSceneFromDB(ctx);

        ensureHostNotExists(userModel);

        saveHost(ctx, sceneFromDB, userModel);
    }


    private SceneModel getSceneFromDB(CreateHostContext ctx) {
        Optional<SceneModel> sceneFromDB = sceneRepository.findByName(ctx.getSceneName());
        if (sceneFromDB.isEmpty()) {
            throw new DataNotFoundException(String.format("Scene %s was not found", ctx.getSceneName()));
        }

        return sceneFromDB.get();
    }

    private void ensureHostNotExists(UserModel userModel) {
        Optional<HostModel> currentHost = hostsRepository.findByUserModelLogin(userModel.getLogin());
        if (currentHost.isPresent()) {
            throw new UselessOperationException("This user is already a host");
        }
    }

    private void saveHost(CreateHostContext ctx, SceneModel sceneFromDB, UserModel userModel) {
        HostModel hostModel = new HostModel();
        hostModel.setUri(ctx.getUri());
        hostModel.setScene(sceneFromDB);
        hostModel.setUser(userModel);

        hostsRepository.save(hostModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() {
        HostModel currentHost = getHostFromDB();

        hostsRepository.delete(currentHost);
    }

    private HostModel getHostFromDB() {
        UserModel userModel = getCurrentUser();
        Optional<HostModel> currentHost = hostsRepository.findByUserModelLogin(userModel.getLogin());
        if (currentHost.isEmpty()) {
            throw new UselessOperationException("This user is not the host");
        }

        return currentHost.get();
    }

    private UserModel getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = (String) securityContext.getAuthentication().getPrincipal();

        return usersRepository.findByLogin(login).get();
    }
}
