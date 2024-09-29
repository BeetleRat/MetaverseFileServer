package com.metaverse.files.ro.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.metaverse.files.ro.response.details.ErrorDetailsRO;
import com.metaverse.files.ro.response.details.InfoDetailsRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rest Object результата выполнения запроса.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Информация о выполненном запросе")
public class ResultDetailsRO {

    @Schema(description = "Была ли запрос успешно выполнена")
    private boolean success = false;
    @Schema(description = "Список информирующих сообщений")
    private List<InfoDetailsRO> info;
    @Schema(description = "Список предупреждений")
    private List<InfoDetailsRO> warning;
    @Schema(description = "Список ошибок, в случае если запрос не был выполнен успешно")
    private List<ErrorDetailsRO> error;

    /**
     * Фабричный метод создания успешного результата выполнения запроса.
     *
     * @return успешный результат выполнения запроса
     */
    public static ResultDetailsRO success() {
        return new ResultDetailsRO().withSuccess(true);
    }

    /**
     * Добавить информирующие сообщения к результату выполнения запроса.
     *
     * @param details первое {@link InfoDetailsRO информирующее сообщение}
     * @param rest    остальные {@link InfoDetailsRO информирующие сообщения}
     * @return self
     */
    public ResultDetailsRO withInfo(InfoDetailsRO details, InfoDetailsRO... rest) {
        if (info == null) {
            info = new ArrayList<>();
        }
        info.add(details);
        Collections.addAll(info, rest);

        return this;
    }

    /**
     * Добавить предупреждения к результату выполнения запроса.
     *
     * @param details первое {@link InfoDetailsRO предупреждение}
     * @param rest    остальные {@link InfoDetailsRO предупреждения}
     * @return self
     */
    public ResultDetailsRO withWarning(InfoDetailsRO details, InfoDetailsRO... rest) {
        if (warning == null) {
            warning = new ArrayList<>();
        }
        warning.add(details);
        Collections.addAll(warning, rest);

        return this;
    }

    /**
     * Добавить ошибку к результату выполнения запроса.
     *
     * @param details первая {@link ErrorDetailsRO ошибка}
     * @param rest    остальные {@link ErrorDetailsRO ошибки}
     * @return self
     */
    public ResultDetailsRO withError(ErrorDetailsRO details, ErrorDetailsRO... rest) {
        if (error == null) {
            error = new ArrayList<>();
        }
        error.add(details);
        Collections.addAll(error, rest);

        return this;
    }

    /**
     * Указать статус успешности выполнения запроса.
     *
     * @param success была ли запрос успешно выполнен
     * @return self
     */
    public ResultDetailsRO withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * @return true, если запрос успешно выполнен
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success была ли запрос успешно выполнен
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return список {@link InfoDetailsRO информирующих сообщений}
     */
    public List<InfoDetailsRO> getInfo() {
        return info;
    }

    /**
     * @param info список {@link InfoDetailsRO информирующих сообщений}
     */
    public void setInfo(List<InfoDetailsRO> info) {
        this.info = info;
    }

    /**
     * @return список {@link InfoDetailsRO предупреждений}
     */
    public List<InfoDetailsRO> getWarning() {
        return warning;
    }

    /**
     * @param warning список {@link InfoDetailsRO предупреждений}
     */
    public void setWarning(List<InfoDetailsRO> warning) {
        this.warning = warning;
    }

    /**
     * @return список {@link ErrorDetailsRO ошибок}
     */
    public List<ErrorDetailsRO> getError() {
        return error;
    }

    /**
     * @param error список {@link ErrorDetailsRO ошибок}
     */
    public void setError(List<ErrorDetailsRO> error) {
        this.error = error;
    }
}
