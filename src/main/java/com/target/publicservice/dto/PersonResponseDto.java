package com.target.publicservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonResponseDto {

    private PersonDto person;
    private List<PhoneDto> phones;
    private List<AddressDto> addresses;
}
