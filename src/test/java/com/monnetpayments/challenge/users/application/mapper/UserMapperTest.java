package com.monnetpayments.challenge.users.application.mapper;

import com.monnetpayments.challenge.users.application.dto.AddressDto;
import com.monnetpayments.challenge.users.application.dto.CompanyDto;
import com.monnetpayments.challenge.users.application.dto.GeoDto;
import com.monnetpayments.challenge.users.application.dto.UserDto;
import com.monnetpayments.challenge.users.application.dto.external.AddressExternalDto;
import com.monnetpayments.challenge.users.application.dto.external.CompanyExternalDto;
import com.monnetpayments.challenge.users.application.dto.external.GeoExternalDto;
import com.monnetpayments.challenge.users.application.dto.external.JsonPlaceholderUserDto;
import com.monnetpayments.challenge.users.domain.model.Address;
import com.monnetpayments.challenge.users.domain.model.Company;
import com.monnetpayments.challenge.users.domain.model.Geo;
import com.monnetpayments.challenge.users.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void shouldMapUserToUserDto() {
        User user = buildUser();

        UserDto dto = mapper.toDto(user);

        assertNotNull(dto);
        assertEquals(user.getExternalId(), dto.getId());
        assertEquals(user.getName(), dto.getName());
        assertEquals(user.getAddress().getStreet(), dto.getAddress().getStreet());
        assertEquals(user.getCompany().getName(), dto.getCompany().getName());
    }

    @Test
    void shouldMapExternalDtoToUser() {
        JsonPlaceholderUserDto external = buildExternalUser();

        User user = mapper.toEntity(external);

        assertNotNull(user);
        assertEquals(external.getId(), user.getExternalId());
        assertEquals(external.getName(), user.getName());
        assertEquals(external.getAddress().getGeo().getLat(), user.getAddress().getGeo().getLat());
    }

    @Test
    void shouldMapExternalDtoListToUserList() {
        List<JsonPlaceholderUserDto> externals = Collections.singletonList(buildExternalUser());

        List<User> users = mapper.toEntityList(externals);

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getExternalId());
    }

    @Test
    void shouldMapUserListToUserDtoList() {
        List<User> users = Collections.singletonList(buildUser());

        List<UserDto> dtos = mapper.toDtoList(users);

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        assertEquals(users.get(0).getExternalId(), dtos.get(0).getId());
    }

    @Test
    void shouldMapUserDtoToUser() {
        UserDto dto = buildUserDto();

        User user = mapper.toEntity(dto);

        assertNotNull(user);
        assertEquals(dto.getId(), user.getExternalId());
        assertEquals(dto.getName(), user.getName());
        assertEquals(dto.getAddress().getGeo().getLat(), user.getAddress().getGeo().getLat());
        assertEquals(dto.getCompany().getName(), user.getCompany().getName());
    }

    private User buildUser() {
        return User.builder()
                .id(100L)
                .externalId(1)
                .name("Leanne Graham")
                .username("Bret")
                .email("Sincere@april.biz")
                .phone("1-770-736-8031 x56442")
                .website("hildegard.org")
                .address(Address.builder()
                        .street("Kulas Light")
                        .suite("Apt. 556")
                        .city("Gwenborough")
                        .zipcode("92998-3874")
                        .geo(Geo.builder().lat("-37.3159").lng("81.1496").build())
                        .build())
                .company(Company.builder()
                        .name("Romaguera-Crona")
                        .catchPhrase("Multi-layered client-server neural-net")
                        .bs("harness real-time e-markets")
                        .build())
                .build();
    }

    private JsonPlaceholderUserDto buildExternalUser() {
        return JsonPlaceholderUserDto.builder()
                .id(1)
                .name("Leanne Graham")
                .username("Bret")
                .email("Sincere@april.biz")
                .phone("1-770-736-8031 x56442")
                .website("hildegard.org")
                .address(AddressExternalDto.builder()
                        .street("Kulas Light")
                        .suite("Apt. 556")
                        .city("Gwenborough")
                        .zipcode("92998-3874")
                        .geo(GeoExternalDto.builder().lat("-37.3159").lng("81.1496").build())
                        .build())
                .company(CompanyExternalDto.builder()
                        .name("Romaguera-Crona")
                        .catchPhrase("Multi-layered client-server neural-net")
                        .bs("harness real-time e-markets")
                        .build())
                .build();
    }

    private UserDto buildUserDto() {
        return UserDto.builder()
                .id(1)
                .name("Leanne Graham")
                .username("Bret")
                .email("Sincere@april.biz")
                .phone("1-770-736-8031 x56442")
                .website("hildegard.org")
                .address(AddressDto.builder()
                        .street("Kulas Light")
                        .suite("Apt. 556")
                        .city("Gwenborough")
                        .zipcode("92998-3874")
                        .geo(GeoDto.builder().lat("-37.3159").lng("81.1496").build())
                        .build())
                .company(CompanyDto.builder()
                        .name("Romaguera-Crona")
                        .catchPhrase("Multi-layered client-server neural-net")
                        .bs("harness real-time e-markets")
                        .build())
                .build();
    }
}
