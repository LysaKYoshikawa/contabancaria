package com.yoshikawa.contabancaria.app.mapper;

import com.yoshikawa.contabancaria.app.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.entity.UserRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "agency", source = "agency")
    @Mapping(target = "account", source = "account")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "document", source = "document")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "statusType", source = "statusType")
    UserRequestEntity toModel(UserDTO userDTO);


}
