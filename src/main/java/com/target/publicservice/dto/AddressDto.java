package com.target.publicservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private Long id;
    private String city;
    private Long zipCode;
    private Long personId;
    private String publicArea;
    private String neighborhood;
    private String federativeUnit;
}
