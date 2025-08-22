package com.yoshikawa.contabancaria.app.services.impl;

import com.yoshikawa.contabancaria.app.DTOs.UserDTO;
import com.yoshikawa.contabancaria.app.mapper.UserMapper;
import com.yoshikawa.contabancaria.domain.entity.UserRequestEntity;
import com.yoshikawa.contabancaria.infra.dataProvider.UserCreateDataProvider;
import org.springframework.http.ResponseEntity;

public class UserResquestServiceImpl {
    private final UserMapper userMapper;
    private final UserCreateDataProvider userCreateDataProvider;

    public UserResquestServiceImpl(UserMapper userMapper, UserCreateDataProvider userCreateDataProvider) {
        this.userMapper = userMapper;
        this.userCreateDataProvider = userCreateDataProvider;
    }

    public ResponseEntity<String> createUser(UserDTO userDTO) {
        UserRequestEntity userRequestEntity = userMapper.toModel(userDTO);
        // Logic to create a user
        return userCreateDataProvider.createUser(userRequestEntity.toString());
    }
}
