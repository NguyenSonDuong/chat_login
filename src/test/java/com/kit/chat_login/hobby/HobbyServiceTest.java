package com.kit.chat_login.hobby;

import com.kit.chat_login.dto.HobbyDto;
import com.kit.chat_login.exception.HobbyException;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.service.hobby.HobbyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class HobbyServiceTest {

    @Autowired
    HobbyService hobbyService;

    @Test
    public void createHobby(){

        assertThatThrownBy(()->{
            HobbyDto hobbyDto1 = hobbyService.createHobby("","");
        });
        assertThatThrownBy(()->{
            HobbyDto hobbyDto7 =hobbyService.createHobby("   ","    ");
        });
        assertThatThrownBy(()->{
            HobbyDto hobbyDto8 =hobbyService.createHobby("    ","Chơi bài nào");
        });

        HobbyDto hobbyDto2 =hobbyService.createHobby("Đọc sách","");
        assertThat(hobbyDto2)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Đọc sách")
                .hasFieldOrPropertyWithValue("description","")
                .hasFieldOrPropertyWithValue("status", StatusModel.ACTIVE);

        HobbyDto hobbyDto3 =hobbyService.createHobby("Xem phim","Xem các bộ phim hành động");
        assertThat(hobbyDto3)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Xem phim")
                .hasFieldOrPropertyWithValue("description","Xem các bộ phim hành động")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);

        HobbyDto hobbyDto4 =hobbyService.createHobby("Nghe nhạc","Các bài nhạc cổ");
        assertThat(hobbyDto4)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Nghe nhạc")
                .hasFieldOrPropertyWithValue("description","Các bài nhạc cổ")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);

        HobbyDto hobbyDto5 =hobbyService.createHobby("   Chơi đàn   ","Chơi piano");
        assertThat(hobbyDto5)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Chơi đàn")
                .hasFieldOrPropertyWithValue("description","Chơi piano")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);

        HobbyDto hobbyDto6 =hobbyService.createHobby("Chơi Game   ","Game hành động");
        assertThat(hobbyDto6)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Chơi Game")
                .hasFieldOrPropertyWithValue("description","Game hành động")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);


    }

    @Test
    public void updateHooby(){
        assertThatThrownBy(()->{
            HobbyDto hobbyDto = hobbyService.updateHooby(-1,"","");
        });
        assertThatThrownBy(()->{
            HobbyDto hobbyDto = hobbyService.updateHooby(1,"","");
        });
        assertThatThrownBy(()->{
            HobbyDto hobbyDto = hobbyService.updateHooby(-1,"Test sachs","");

        });
        assertThatThrownBy(()->{
            HobbyDto hobbyDto = hobbyService.updateHooby(7,"Test sachs","");

        });
        assertThatThrownBy(()->{
            HobbyDto hobbyDto = hobbyService.updateHooby(7,"","");

        });

        HobbyDto hobbyDto2 =hobbyService.updateHooby(1,"Đọc sách","");
        assertThat(hobbyDto2)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Đọc sách")
                .hasFieldOrPropertyWithValue("description","")
                .hasFieldOrPropertyWithValue("status", StatusModel.ACTIVE);

        HobbyDto hobbyDto3 =hobbyService.updateHooby(1,"Xem phim","Xem các bộ phim hành động");
        assertThat(hobbyDto3)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Xem phim")
                .hasFieldOrPropertyWithValue("description","Xem các bộ phim hành động")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);

        HobbyDto hobbyDto4 =hobbyService.updateHooby(1,"Nghe nhạc","Các bài nhạc cổ");
        assertThat(hobbyDto4)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Nghe nhạc")
                .hasFieldOrPropertyWithValue("description","Các bài nhạc cổ")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);

        HobbyDto hobbyDto5 =hobbyService.updateHooby(1,"   Chơi đàn   ","Chơi piano");
        assertThat(hobbyDto5)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Chơi đàn")
                .hasFieldOrPropertyWithValue("description","Chơi piano")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);

        HobbyDto hobbyDto6 =hobbyService.updateHooby(1,"Chơi Game   ","Game hành động");
        assertThat(hobbyDto6)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Chơi Game")
                .hasFieldOrPropertyWithValue("description","Game hành động")
                .hasFieldOrPropertyWithValue("status",StatusModel.ACTIVE);
    }

    @Test
    public void deleteHobby(){
        assertThatThrownBy(()->{
            boolean hobbyDto = hobbyService.deleteHobby(-1);
        });
        assertThatThrownBy(()->{
            boolean hobbyDto = hobbyService.deleteHobby(11);
        });
        boolean hobbyDto = hobbyService.deleteHobby(6);
        assertThat(hobbyDto).isTrue();
    }

    @Test
    public void readHobbyByID(){
        HobbyDto hobbyDto2 =hobbyService.readHobby(1);
        assertThat(hobbyDto2)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","Chơi Game")
                .hasFieldOrPropertyWithValue("description","Game hành động")
                .hasFieldOrPropertyWithValue("_id",1)
                .hasFieldOrPropertyWithValue("status", StatusModel.ACTIVE);
    }

    @Test
    public void readHobbyByName(){
        Page<HobbyDto> hobbyDtos = hobbyService.readHobby("Chơi",Pageable.ofSize(10));
    }

    @Test
    public void readAllHobby(){

    }
}
