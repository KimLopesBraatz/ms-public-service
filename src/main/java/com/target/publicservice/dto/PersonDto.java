package com.target.publicservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class PersonDto {

    private Long id;
    private String name;
    private String itin;
    private String email;
    private Date createdIn;
    private LocalDate dateOfBirth;
}
