package com.kit.chat_login.repository.adress;

import com.kit.chat_login.model.user.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}