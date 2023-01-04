package com.kit.chat_login.help;

import com.kit.chat_login.responsive.Base;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String)reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = Help.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
