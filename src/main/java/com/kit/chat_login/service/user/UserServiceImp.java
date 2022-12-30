package com.kit.chat_login.service.user;


import com.kit.chat_login.dto.TokenDto;
import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.exception.UserException;
import com.kit.chat_login.mapping.TokenMapping;
import com.kit.chat_login.mapping.UserInfoMapping;
import com.kit.chat_login.mapping.UserMapping;
import com.kit.chat_login.message.user.UserErrorMessage;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.authen.Role;
import com.kit.chat_login.model.token.Token;
import com.kit.chat_login.model.user.UserInfo;
import com.kit.chat_login.model.user.hobby.Hobby;
import com.kit.chat_login.repository.UserRepository;
import com.kit.chat_login.repository.authen.RoleRepository;
import com.kit.chat_login.repository.hobby.HobbyRepository;
import com.kit.chat_login.repository.token.TokenRepository;
import com.kit.chat_login.security.jwt.JwtUtil;
import com.kit.chat_login.security.jwt.userdetail.UserDetailsImp;
import com.kit.chat_login.service.authen.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    HobbyRepository hobbyRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    RoleService roleService;
    @Override
    public TokenDto login(String username, String password) {
        String regex = "^(\\S+)@([\\S]+)$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(username);
        Token token = new Token();
        if(!matcher.find()){
            User user = userRepository.findByUsername(username);
            if(user == null)
                throw new UserException(UserErrorMessage.LOGIN_ERROR);
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())){
                UserDetailsImp userPrincipal = UserMapping.convertUserDetailsImp(user);
                token.setToken(jwtUtil.generateToken(userPrincipal));
                token.setToken_exp(jwtUtil.generateExpirationDate());
                token.setStatus(StatusModel.ACTIVE);
                Token token1 = tokenRepository.save(token);
                if(token1 == null)
                    throw new UserException(UserErrorMessage.TOKEN_NOT_SAVE);
                return TokenMapping.convert(token1);
            }else{
                throw new UserException("Username or password were wrong");
            }

        }else{
            User user = userRepository.findByEmail(username);
            if(user == null)
                throw new UserException(UserErrorMessage.LOGIN_ERROR);
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())){
                UserDetailsImp userPrincipal = UserMapping.convertUserDetailsImp(user);
                token.setToken(jwtUtil.generateToken(userPrincipal));
                token.setToken_exp(jwtUtil.generateExpirationDate());
                token.setStatus(StatusModel.ACTIVE);
                Token token1 = tokenRepository.save(token);
                if(token1 == null)
                    throw new UserException(UserErrorMessage.TOKEN_NOT_SAVE);
                return TokenMapping.convert(token1);
            }else{
                throw new UserException(UserErrorMessage.LOGIN_ERROR);
            }

        }
    }

    @Override
    public UserDto register(String username, String email, String password) {

        if(username.isBlank() || email.isBlank() || password.isBlank()){
            throw new UserException(UserErrorMessage.DATA_EMPTY);
        }

        if(userRepository.existsByUsernameOrEmail(username,email)){
            throw new UserException(UserErrorMessage.USER_EXITS);
        }

        if(username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()){
            throw new UserException(UserErrorMessage.DATA_EMPTY);
        }

        if(username.length() <=5 || username.length()>=100 ||
                email.length() <=5 || email.length()>=100 ||
                password.length() <=5 || password.length()>=100 ){
            throw new UserException(UserErrorMessage.WRONG_DATA_FORMAT);
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));

        UserInfo userInfo = new UserInfo();
        userInfo.setHobbies(new HashSet<>());

        Role userRole = roleService.readRole("USER");

        user.setRoles(new HashSet<>());
        user.getRoles().add(userRole);

        user.setStatus(StatusModel.ACTIVE);

        User userSave = userRepository.saveAndFlush(user);
        if(userSave == null)
            throw new UserException(UserErrorMessage.CREATE_ERROR);
        return UserMapping.convert(userSave);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public Page<UserDto> searchUser(String username,Pageable pageable) {

        return null;
    }

}
