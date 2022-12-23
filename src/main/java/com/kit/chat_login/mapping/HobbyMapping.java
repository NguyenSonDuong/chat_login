package com.kit.chat_login.mapping;

import com.kit.chat_login.dto.HobbyDto;
import com.kit.chat_login.model.user.hobby.Hobby;

public class HobbyMapping {
    public static HobbyDto convert(Hobby hobby){
        HobbyDto hobbyDto = new HobbyDto(
          hobby.getStatus(),
          hobby.getName(),
          hobby.getDescription()
        );
        return hobbyDto;
    }
}
