package com.monnetpayments.challenge.users.application.dto.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressExternalDto {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoExternalDto geo;
}
