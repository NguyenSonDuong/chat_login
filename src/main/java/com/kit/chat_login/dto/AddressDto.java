package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kit.chat_login.model.user.address.Address} entity
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class AddressDto implements Serializable {
    private final StatusModel status;
    private final String nation;
    private final String city;
    private final String state;
    private final String address_one;
    private final String address_two;
}