package com.kit.chat_login.service.hobby;

import com.kit.chat_login.dto.HobbyDto;
import com.kit.chat_login.exception.HobbyException;
import com.kit.chat_login.mapping.HobbyMapping;
import com.kit.chat_login.message.hobby.HobbyErrorMessage;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.user.UserInfo;
import com.kit.chat_login.model.user.hobby.Hobby;
import com.kit.chat_login.repository.hobby.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class HobbyServiceImp implements HobbyService{

    @Autowired
    HobbyRepository hobbyRepository;

    @Override
    public HobbyDto createHobby(String name, String description) {

        if(name.isBlank() || name.trim().isEmpty())
            throw new HobbyException(HobbyErrorMessage.DATA_EMPTY);

        Hobby hobby = new Hobby();
        hobby.setStatus(StatusModel.ACTIVE);
        hobby.setName(name.trim());
        if(description != null)
            hobby.setDescription(description.trim());

        Hobby hobbySave = hobbyRepository.saveAndFlush(hobby);
        if(hobbySave == null)
            throw new HobbyException(HobbyErrorMessage.CREATE_ERROR);
        return HobbyMapping.convert(hobby);
    }

    @Override
    public HobbyDto updateHooby(int id, String name, String description) {
        if(name.isBlank())
            throw new HobbyException(HobbyErrorMessage.DATA_EMPTY);

        Hobby hobby = hobbyRepository.findBy_id(id);
        if(hobby==null)
            throw new HobbyException(HobbyErrorMessage.NOT_FOUND);
        hobby.setName(name.trim());
        if(description != null)
            hobby.setDescription(description.trim());
        Hobby hobbySave = hobbyRepository.save(hobby);
        if(hobbySave==null)
            throw new HobbyException(HobbyErrorMessage.UPDATE_ERROR);
        return HobbyMapping.convert(hobbySave);
    }

    @Override
    public boolean deleteHobby(int id) {
        try{
            hobbyRepository.deleteById(id);
        }catch (Exception ex){
            throw new HobbyException(HobbyErrorMessage.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public HobbyDto readHobby(int id) {
        Hobby hobby = hobbyRepository.findBy_id(id);
        if(hobby==null)
            throw new HobbyException(HobbyErrorMessage.NOT_FOUND);
        return HobbyMapping.convert(hobby);
    }

    @Override
    public Page<HobbyDto> readHobby(String name, Pageable pageable) {

        if(name.isBlank())
            throw new HobbyException(HobbyErrorMessage.DATA_EMPTY);

        Page<Hobby> hobbies = hobbyRepository.findByNameLike(name, pageable);
        if(hobbies == null || hobbies.isEmpty())
            throw new HobbyException(HobbyErrorMessage.NOT_FOUND);
        System.out.println( hobbies.getContent().get(0).getUserInfos().toString());
        Page<HobbyDto> hobbydto = hobbies.map(new Function<Hobby, HobbyDto>() {
            @Override
            public HobbyDto apply(Hobby hobby) {
                return HobbyMapping.convert(hobby);
            }
        });
        return hobbydto;
    }

    @Override
    public Page<HobbyDto> readAllHobby( Pageable pageable) {
        Page<Hobby> hobbies = hobbyRepository.findAll(pageable);
        if(hobbies == null || hobbies.isEmpty())
            throw new HobbyException(HobbyErrorMessage.NOT_FOUND);
        return null;
    }
}
