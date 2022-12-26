package com.kit.chat_login.service.user;


import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.mapping.UserInfoMapping;
import com.kit.chat_login.mapping.UserMapping;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.user.UserInfo;
import com.kit.chat_login.model.user.hobby.Hobby;
import com.kit.chat_login.repository.UserRepository;
import com.kit.chat_login.repository.hobby.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.function.Function;

@Component
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    HobbyRepository hobbyRepository;


    @Override
    public UserDto login(String username, String password) {
        return null;
    }

    @Override
    public UserDto register(String username, String email, String password) {
        Set<Hobby> hobbies = new HashSet<>(hobbyRepository.findAll());
        UserInfo userInfo = new UserInfo();
//        List<UserHobby> userHobbies = new ArrayList<>();
//        for (Hobby item :
//                hobbies) {
//            UserHobby userHobby = new UserHobby();
//            userHobby.setHobby(item);
//            userHobby.setUser(userInfo);
//        }
//        userInfo.setUserHobbies(new HashSet<>(userHobbies));
        userInfo.setHobbies(hobbies);
        userInfo.setFullname("Nguyen Duong");
        User user = new User();
        user.setUserInfo(userInfo);
        user.setEmail("nguyenduong@gmail.com");
        user.setUsername("nguyenduong");
        user.setPassword("$#%sdfgsd2345");
        user.setTime_disable(null);
        userRepository.saveAndFlush(user);
        return UserMapping.convert(user);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public List<UserDto> searchUser() {
        return null;
    }

    @Override
    public Page<UserDto> getAll(Pageable page) {
        Page<User> pageUser = userRepository.findAll(page);
        Page<UserDto> dtoPage = pageUser.map(new Function<User, UserDto>() {
            @Override
            public UserDto apply(User entity) {
                UserDto dto = new UserDto(
                        entity.getStatus(),
                        entity.getCreate_at(),
                        entity.getUuid(),
                        entity.getUuid(),
                        entity.getUsername(),
                        entity.getTime_disable(),
                        UserInfoMapping.convert(entity.getUserInfo())
                );

                return dto;
            }

        });
        return dtoPage;
    }
}
