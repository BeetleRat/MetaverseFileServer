package com.metaverse.files.converters;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import static java.util.stream.Collectors.toList;

/**
 * Базовый класс конвертации одного типа в другой.
 *
 * @author Mikhail.Kataranov
 * @since 02.11.2024
 */
public abstract class Converter<Input, Output> {

    /**
     * Из бизнес логики в Rest.
     */
    protected Function<Input, Output> to;
    /**
     * Из Rest в бизнес логику.
     */
    protected Function<Output, Input> from;

    /**
     * Конструктор.
     */
    protected Converter() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param to   функция преобразования объекта из бизнес логики в Rest объект
     * @param from функция преобразования Rest объекта в объект бизнес логики
     */
    protected Converter(Function<Input, Output> to, Function<Output, Input> from) {
        this();
        this.to = to;
        this.from = from;
    }

    /**
     * Преобразовать объект бизнес логики в Rest объект.
     *
     * @param obj объект бизнес логики
     * @return Rest объект
     */
    @Nullable
    public Output to(Input obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        return this.to.apply(obj);
    }

    /**
     * Преобразовать Rest объект в объект бизнес логики.
     *
     * @param obj Rest объект
     * @return объект бизнес логики
     */
    @Nullable
    public Input from(Output obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        return this.from.apply(obj);
    }

    /**
     * Преобразовать коллекцию объектов бизнес логики в список Rest объектов.
     *
     * @param objs коллекция объектов бизнес логики
     * @return список Rest объектов
     */
    public List<Output> to(Collection<Input> objs) {
        if (CollectionUtils.isEmpty(objs)) {
            return Collections.emptyList();
        }

        return objs.stream()
                .map(this::to)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    /**
     * Преобразовать коллекцию Rest объектов в список объектов бизнес логики.
     *
     * @param objs коллекция Rest объектов
     * @return список объектов бизнес логики
     */
    public List<Input> from(Collection<Output> objs) {
        if (CollectionUtils.isEmpty(objs)) {
            return Collections.emptyList();
        }

        return objs.stream()
                .map(this::from)
                .filter(Objects::nonNull)
                .collect(toList());
    }
}
