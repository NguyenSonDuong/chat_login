package com.kit.chat_login.service.authen.permission;

import com.kit.chat_login.dto.PermissionDto;
import com.kit.chat_login.exception.PermissionException;
import com.kit.chat_login.mapping.PermissionMapping;
import com.kit.chat_login.message.auth.PermissionErrorMessage;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.authen.Permission;
import com.kit.chat_login.repository.authen.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PermissionServiceImp implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public PermissionDto createPermisstion(String name, String description) {
        if(name.isBlank() || name.trim().isEmpty())
            throw new PermissionException(PermissionErrorMessage.DATA_EMPTY);
        Permission permission = new Permission();
        permission.setStatus(StatusModel.ACTIVE);
        permission.setName(name.trim());
        if(description != null)
            permission.setDescription(description.trim());
        Permission permission1 = permissionRepository.save(permission);
        if(permission1 == null)
            throw new PermissionException(PermissionErrorMessage.CREATE_ERROR);
        return PermissionMapping.convert(permission1);
    }

    @Override
    public PermissionDto updatePermisstion(int id, String name, String description) {
        if(name.isBlank() || name.trim().isEmpty())
            throw new PermissionException(PermissionErrorMessage.DATA_EMPTY);
        Permission permission = permissionRepository.findBy_id(id);
        if(permission == null)
            throw new PermissionException(PermissionErrorMessage.NOT_FOUND);
        permission.setName(name.trim());
        if(description != null)
            permission.setDescription(description.trim());
        Permission permission1 = permissionRepository.save(permission);
        if(permission1 == null)
            throw new PermissionException(PermissionErrorMessage.CREATE_ERROR);
        return PermissionMapping.convert(permission1);
    }

    @Override
    public boolean deletePermisstion(int id) {
        try{
            permissionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public PermissionDto readPermisstion(int id) {
        Permission permission = permissionRepository.findBy_id(id);
        if(permission == null)
            throw new PermissionException(PermissionErrorMessage.NOT_FOUND);
        return PermissionMapping.convert(permission);
    }

    @Override
    public Page<PermissionDto> readPermisstion(String name, Pageable pageable) {
        if(name.isBlank() || name.trim().isEmpty())
            throw new PermissionException(PermissionErrorMessage.DATA_EMPTY);
        Page<Permission> permissions = permissionRepository.findByNameLike(name.trim(),pageable);
        if(permissions == null)
            throw new PermissionException(PermissionErrorMessage.NOT_FOUND);
        Page<PermissionDto> permissionDtos = permissions.map(new Function<Permission, PermissionDto>() {
            @Override
            public PermissionDto apply(Permission permission) {
                return PermissionMapping.convert(permission);
            }
        });
        return permissionDtos;
    }

    @Override
    public Page<PermissionDto> readAllPermisstion(Pageable pageable) {
        Page<Permission> permissions = permissionRepository.findAll(pageable);
        if(permissions == null)
            throw new PermissionException(PermissionErrorMessage.NOT_FOUND);
        Page<PermissionDto> permissionDtos = permissions.map(new Function<Permission, PermissionDto>() {
            @Override
            public PermissionDto apply(Permission permission) {
                return PermissionMapping.convert(permission);
            }
        });
        return permissionDtos;
    }
}
