package com.metaverse.files.converters.role;

import com.metaverse.files.converters.Converter;
import com.metaverse.files.models.PermissionModel;
import com.metaverse.files.ro.role.PermissionRO;
import org.springframework.stereotype.Component;

/**
 * Конвертер PermissionModel в PermissionRO.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class PermissionConverter extends Converter<PermissionModel, PermissionRO> {

    /**
     * Конструктор.
     */
    public PermissionConverter() {
        super();
        this.from = null;
        this.to = this::convert;
    }

    private PermissionRO convert(PermissionModel model) {
        PermissionRO ro = new PermissionRO();
        ro.setName(model.getName());

        return ro;
    }
}
