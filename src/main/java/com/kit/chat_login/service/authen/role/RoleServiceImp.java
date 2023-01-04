package com.kit.chat_login.service.authen.role;

import com.kit.chat_login.dto.RoleDto;
import com.kit.chat_login.exception.RoleException;
import com.kit.chat_login.mapping.RoleMapping;
import com.kit.chat_login.message.auth.RoleErrorMessage;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.authen.Permission;
import com.kit.chat_login.model.authen.Role;
import com.kit.chat_login.repository.authen.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Component
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleDto createRole(String name, String description) {
        if(name.isBlank() || name.trim().isEmpty())
            throw new RoleException(RoleErrorMessage.DATA_EMPTY);

        Set<Permission> permissions = new HashSet<>();
        permissions.add(new Permission(name+"_READ","", StatusModel.ACTIVE));
        permissions.add(new Permission(name+"_UPDATE","", StatusModel.ACTIVE));
        permissions.add(new Permission(name+"_DELETE","", StatusModel.ACTIVE));
        permissions.add(new Permission(name+"_CREATE","", StatusModel.ACTIVE));

        Role role = new Role();
        role.setStatus(StatusModel.ACTIVE);
        role.setName(name.trim());
        role.setPermissions(permissions);
        if(description != null)
            role.setDescription(description.trim());
        Role role1 = roleRepository.saveAndFlush(role);
        if(role1 == null)
            throw new RoleException(RoleErrorMessage.CREATE_ERROR);
        return RoleMapping.convert(role1);
    }

    @Override
    public RoleDto updateRole(int id, String name, String description) {
        if(name.isBlank() || name.trim().isEmpty())
            throw new RoleException(RoleErrorMessage.DATA_EMPTY);
        Role role = roleRepository.findBy_id(id);
        if(role == null)
            throw new RoleException(RoleErrorMessage.NOT_FOUND);
        role.setName(name.trim());
        if(description != null)
            role.setDescription(description.trim());
        Role role1 = roleRepository.save(role);
        if(role1 == null)
            throw new RoleException(RoleErrorMessage.CREATE_ERROR);
        return RoleMapping.convert(role1);
    }

    @Override
    public boolean deleteRole(int id) {
        try{
            roleRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public RoleDto readRole(int id) {
        Role role = roleRepository.findBy_id(id);
        if(role == null)
            throw new RoleException(RoleErrorMessage.NOT_FOUND);
        return RoleMapping.convert(role);
    }

    @Override
    public Role readRole(String name) {
        if(name.isBlank() || name.trim().isEmpty())
            throw new RoleException(RoleErrorMessage.DATA_EMPTY);
        Role role = roleRepository.findByName(name);
        if(role == null)
            throw new RoleException(RoleErrorMessage.NOT_FOUND);
        return role;
    }

    @Override
    public Page<RoleDto> readRole(String name, Pageable pageable) {
        if(name.isBlank() || name.trim().isEmpty())
            throw new RoleException(RoleErrorMessage.DATA_EMPTY);
        Page<Role> roles = roleRepository.findByNameLike(name.trim(),pageable);
        if(roles == null)
            throw new RoleException(RoleErrorMessage.NOT_FOUND);
        Page<RoleDto> roleDtos = roles.map(new Function<Role, RoleDto>() {
            @Override
            public RoleDto apply(Role role) {
                return RoleMapping.convert(role);
            }
        });
        return roleDtos;
    }

    @Override
    public Page<RoleDto> readAllRole(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        if(roles == null)
            throw new RoleException(RoleErrorMessage.NOT_FOUND);
        Page<RoleDto> roleDtos = roles.map(new Function<Role, RoleDto>() {
            @Override
            public RoleDto apply(Role role) {
                return RoleMapping.convert(role);
            }
        });
        return roleDtos;
    }
}
