package com.monnetpayments.challenge.users.application.dto.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonPlaceholderUserDto {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressExternalDto address;
    private CompanyExternalDto company;
}
