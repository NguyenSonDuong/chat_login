package com.kit.chat_login.responsive;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Base {
    private String status;
    private String title;
    private Object content;

}
