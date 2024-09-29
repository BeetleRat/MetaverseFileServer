package com.metaverse.files.services.role;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.metaverse.files.converters.role.PermissionConverter;
import com.metaverse.files.converters.role.RoleConverter;
import com.metaverse.files.models.PermissionModel;
import com.metaverse.files.models.RoleModel;
import com.metaverse.files.repositories.PermissionRepository;
import com.metaverse.files.repositories.RoleRepository;
import com.metaverse.files.ro.role.PermissionRO;
import com.metaverse.files.ro.role.RoleRO;
import com.metaverse.files.utils.exceptions.UselessOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса ролей.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private PermissionConverter permissionConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoleRO> getAll() {
        List<RoleModel> rolesFromDB = roleRepository.findAll();
        return roleConverter.to(rolesFromDB);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(RoleRO roleRO) {
        RoleModel role = getRoleFromDB(roleRO);
        role.getPermissions().clear();

        List<PermissionModel> existingPermissions = getExistingPermissions(roleRO);
        Set<PermissionModel> newPermissions = createNewPermissions(roleRO, existingPermissions);

        role.getPermissions().addAll(existingPermissions);
        role.getPermissions().addAll(newPermissions);

        roleRepository.save(role);
    }

    private RoleModel getRoleFromDB(RoleRO roleRO) {
        Optional<RoleModel> roleFromDB = roleRepository.findByName(roleRO.getName());
        return roleFromDB.orElse(new RoleModel());
    }

    private List<PermissionModel> getExistingPermissions(RoleRO roleRO) {
        Set<String> permissionNames = getPermissionNames(roleRO);
        return permissionRepository.findAllByNameIn(permissionNames);
    }

    private static Set<String> getPermissionNames(RoleRO roleRO) {
        return roleRO.getPermissions()
                .stream()
                .map(PermissionRO::getName)
                .collect(Collectors.toSet());
    }

    private Set<PermissionModel> createNewPermissions(RoleRO roleRO, List<PermissionModel> existingPermission) {
        Set<String> existingPermissionsNames = getPermissionNames(existingPermission);
        return roleRO.getPermissions().stream()
                .filter(p -> !existingPermissionsNames.contains(p.getName()))
                .map(p -> permissionConverter.from(p))
                .collect(Collectors.toSet());
    }

    private static Set<String> getPermissionNames(List<PermissionModel> existsPermissions) {
        return existsPermissions.stream()
                .map(PermissionModel::getName)
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) {
        RoleModel roleFromDB = getKeyFromDB(id);
        roleRepository.delete(roleFromDB);
    }

    private RoleModel getKeyFromDB(int id) {
        Optional<RoleModel> roleFromDB = roleRepository.findById(id);
        if (roleFromDB.isEmpty()) {
            final String message = String.format("The role with id %d does not exist", id);
            throw new UselessOperationException(message);
        }

        return roleFromDB.get();
    }
}
