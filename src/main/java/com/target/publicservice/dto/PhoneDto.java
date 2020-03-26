package com.target.publicservice.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class PhoneDto {

    private Long id;
    private Long number;
    private Long personId;
    private String areaCode;
}
