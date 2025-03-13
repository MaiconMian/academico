package com.familyvideo.DTOs;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String addressStreet;
    private Integer addressNumber;
    private String addressPostalCode;
    private String addressCity;
    private String addressCountry;
    private String clientName;
    private String clientLastName;

}
