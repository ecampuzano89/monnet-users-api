package com.monnetpayments.challenge.users.application.dto.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoExternalDto {

    private String lat;
    private String lng;
}
