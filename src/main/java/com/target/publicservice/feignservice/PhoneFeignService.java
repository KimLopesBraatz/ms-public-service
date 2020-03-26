package com.target.publicservice.feignservice;

import com.target.publicservice.dto.PhoneDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "phone-service")
public interface PhoneFeignService {

    @GetMapping("/phone/number/{number}")
    PhoneDto getPhoneByNumber(@PathVariable Long number);

    @GetMapping("/phone/personid/{personId}")
    List<PhoneDto> getPhoneByPersonId(@PathVariable Long personId);

    @PostMapping("/phone/")
    List<PhoneDto> postPhone(@Valid @RequestBody List<PhoneDto> phoneDtoList);
}
