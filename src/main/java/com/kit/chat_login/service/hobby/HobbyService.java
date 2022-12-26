package com.kit.chat_login.service.hobby;

import com.kit.chat_login.dto.HobbyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface HobbyService {
    HobbyDto createHobby(String name, String description);
    HobbyDto updateHooby(int id, String name, String description);
    boolean deleteHobby(int id);
    HobbyDto readHobby(int id);
    Page<HobbyDto> readHobby(String name, Pageable pageable);
    Page<HobbyDto> readAllHobby(Pageable pageable);

}
