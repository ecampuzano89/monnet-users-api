package com.monnetpayments.challenge.users.application.mapper;

import com.monnetpayments.challenge.users.application.dto.UserDto;
import com.monnetpayments.challenge.users.application.dto.external.JsonPlaceholderUserDto;
import com.monnetpayments.challenge.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "externalId", target = "id")
    })
    UserDto toDto(User user);

    @Mappings({
            @Mapping(source = "id", target = "externalId"),
            @Mapping(target = "id", ignore = true)
    })
    User toEntity(UserDto dto);

    @Mappings({
            @Mapping(source = "id", target = "externalId"),
            @Mapping(target = "id", ignore = true)
    })
    User toEntity(JsonPlaceholderUserDto dto);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<JsonPlaceholderUserDto> dtos);
}
