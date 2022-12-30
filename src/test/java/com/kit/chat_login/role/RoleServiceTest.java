package com.kit.chat_login.role;

import com.kit.chat_login.dto.RoleDto;
import com.kit.chat_login.repository.authen.RoleRepository;
import com.kit.chat_login.service.authen.role.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @Test
    public void createTest(){
        assertThatThrownBy(()->{
            roleService.createRole("","");
        });
        RoleDto roleDto = roleService.createRole("USER","");
        assertThat(roleDto).isNotNull();
        RoleDto roleDto2 = roleService.createRole("ADMIN","");
        assertThat(roleDto2).isNotNull();
        RoleDto roleDto3 = roleService.createRole("CUSTOMER","");
        assertThat(roleDto3).isNotNull();
    }


}
