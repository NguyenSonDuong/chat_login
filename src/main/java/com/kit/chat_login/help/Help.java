package com.kit.chat_login.help;

import com.kit.chat_login.responsive.Base;
import org.springframework.http.ResponseEntity;

public class Help {
    public static ResponseEntity<?> Success(String title, Object object){
        return ResponseEntity.ok(
                Base.builder()
                        .title(title)
                        .status("success")
                        .content(object)
                        .build()
        );
    }
    public static ResponseEntity<?> Error(String title, Object object){
        return ResponseEntity.badRequest().body(
                Base.builder()
                        .title(title)
                        .status("error")
                        .content(object)
                        .build()
        );
    }
}
