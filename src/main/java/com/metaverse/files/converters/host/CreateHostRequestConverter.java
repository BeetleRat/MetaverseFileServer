package com.metaverse.files.converters.host;

import com.metaverse.files.contexts.host.CreateHostContext;
import com.metaverse.files.converters.Converter;
import com.metaverse.files.ro.host.requests.CreateHostRequestRO;
import org.springframework.stereotype.Component;

/**
 * Конвертер CreateHostRequestRO в CreateHostContext.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
@Component
public class CreateHostRequestConverter extends Converter<CreateHostContext, CreateHostRequestRO> {

    /**
     * Конвертер.
     */
    public CreateHostRequestConverter() {
        super();
        this.from = this::convert;
        this.to = null;
    }

    private CreateHostContext convert(final CreateHostRequestRO request) {
        return CreateHostContext.builder()
                .uri(request.getUri())
                .sceneName(request.getSceneName())
                .build();
    }
}
