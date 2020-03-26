package com.target.publicservice.resource;

import com.target.publicservice.dto.AddressDto;
import com.target.publicservice.dto.PersonDto;
import com.target.publicservice.dto.PhoneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.target.publicservice.service.PublicService;
import com.target.publicservice.dto.PersonResponseDto;

import javax.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
@CrossOrigin
public class PublicResource {

    private PublicService service;

    @PostMapping("/person/save")
    public ResponseEntity<PersonResponseDto> postPerson(@Valid @RequestBody PersonResponseDto person) {
        return new ResponseEntity<>(service.savePerson(person), HttpStatus.OK);
    }

    @GetMapping("/person/itin/{itin}")
    public ResponseEntity<PersonDto> getPersonOnly(@PathVariable String itin) {
        return new ResponseEntity<>(service.getPersonOnly(itin), HttpStatus.OK);
    }

    @GetMapping("/person/complete/itin/{itin}")
    public ResponseEntity<PersonResponseDto> getPersonComplete(@PathVariable String itin) {
        return new ResponseEntity<>(service.getPersonComplete(itin), HttpStatus.OK);
    }

    @GetMapping("/person")
    public ResponseEntity<List<PersonDto>> getPersonList() {
        return new ResponseEntity<>(service.getPersonList(), HttpStatus.OK);
    }

    @GetMapping("/address/personid/{personId}")
    public ResponseEntity<List<AddressDto>> getAddressesByPersonId(@PathVariable Long personId) {
        return new ResponseEntity<>(service.getAdressesByPersonId(personId), HttpStatus.OK);
    }

    @GetMapping("/phone/personid/{personId}")
    public ResponseEntity<List<PhoneDto>> getPhonesByPersonId(@PathVariable Long personId) {
        return new ResponseEntity<>(service.getPhonesByPersonId(personId), HttpStatus.OK);
    }

}
