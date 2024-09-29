package com.metaverse.files.ro.user.responses;

import com.metaverse.files.ro.response.ResultDetailsRO;
import com.metaverse.files.ro.user.UserRO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Ответ на запрос получения информации о пользователе.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Schema(description = "Результат получения информации о пользователе")
public class UserInfoResultRO extends ResultDetailsRO {

    @Schema(description = "Информация о пользователе")
    private UserRO userInfo;

    /**
     * @return {@link UserRO пользователь}
     */
    public UserRO getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo {@link UserRO пользователь}
     */
    public void setUserInfo(UserRO userInfo) {
        this.userInfo = userInfo;
    }
}
