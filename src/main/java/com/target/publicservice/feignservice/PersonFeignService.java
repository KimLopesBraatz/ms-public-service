package com.target.publicservice.feignservice;

import com.target.publicservice.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "person-service")
public interface PersonFeignService {

    @GetMapping("/person/itin/{itin}")
    PersonDto getPersonByItin(@PathVariable String itin);

    @PostMapping("/person/save")
    PersonDto postPerson(@Valid @RequestBody PersonDto person);

    @GetMapping("/person")
    List<PersonDto> getPersonList();
}
