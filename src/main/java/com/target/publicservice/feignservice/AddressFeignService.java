package com.target.publicservice.feignservice;

import com.target.publicservice.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "address-service")
public interface AddressFeignService {

    @GetMapping("/address/zipcode/{zipCode}")
    AddressDto getAddressByZipCode(@PathVariable Long zipCode);

    @GetMapping("/address/personid/{personId}")
    List<AddressDto> getAddressByPersonId(@PathVariable Long personId);

    @PostMapping("/address/")
    List<AddressDto> postAddress(@Valid @RequestBody List<AddressDto> addressDtoList);
}
