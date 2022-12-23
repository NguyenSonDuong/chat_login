package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kit.chat_login.model.user.setting.Setting} entity
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class SettingDto implements Serializable {
    private final StatusModel status;
    private final int theme_style;
}