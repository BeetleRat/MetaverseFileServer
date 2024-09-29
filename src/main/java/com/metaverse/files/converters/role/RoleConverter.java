package com.metaverse.files.converters.role;

import java.util.Set;
import java.util.stream.Collectors;

import com.metaverse.files.converters.Converter;
import com.metaverse.files.models.PermissionModel;
import com.metaverse.files.models.RoleModel;
import com.metaverse.files.ro.role.PermissionRO;
import com.metaverse.files.ro.role.RoleRO;
import org.springframework.stereotype.Component;

/**
 * Конвертер RegistrationKeyModel в RegistrationKeyRO.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class RoleConverter extends Converter<RoleModel, RoleRO> {

    /**
     * Конструктор.
     */
    public RoleConverter() {
        super();
        this.from = null;
        this.to = this::convert;
    }

    private RoleRO convert(RoleModel model) {
        RoleRO ro = new RoleRO();
        ro.setName(model.getName());
        ro.setPermissions(convertPermissions(model));

        return ro;
    }

    private static Set<PermissionRO> convertPermissions(RoleModel model) {
        return model.getPermissions().stream()
                .map(RoleConverter::convertPermission)
                .collect(Collectors.toSet());
    }

    private static PermissionRO convertPermission(PermissionModel model) {
        PermissionRO ro = new PermissionRO();
        ro.setName(model.getName());

        return ro;
    }
}
