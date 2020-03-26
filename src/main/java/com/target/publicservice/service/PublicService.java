package com.target.publicservice.service;

import com.target.publicservice.dto.AddressDto;
import com.target.publicservice.dto.PersonDto;
import com.target.publicservice.dto.PersonResponseDto;
import com.target.publicservice.dto.PhoneDto;
import com.target.publicservice.feignservice.AddressFeignService;
import com.target.publicservice.feignservice.PersonFeignService;
import com.target.publicservice.feignservice.PhoneFeignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PublicService {

    private PhoneFeignService phoneFeignService;
    private PersonFeignService personFeignService;
    private AddressFeignService addressFeignService;

    public PersonResponseDto getPersonComplete(String itin) {
        PersonDto person = personFeignService.getPersonByItin(itin);
        List<AddressDto> addressList = addressFeignService.getAddressByPersonId(person.getId());
        List<PhoneDto> phoneList = phoneFeignService.getPhoneByPersonId(person.getId());

        return PersonResponseDto.builder()
                .person(person)
                .phones(phoneList)
                .addresses(addressList)
                .build();
    }

    public PersonDto getPersonOnly(String itin) {
        return personFeignService.getPersonByItin(itin);
    }

    public PersonResponseDto savePerson(PersonResponseDto personResponseDto) {
        List<PhoneDto> phoneDtos = new ArrayList<>();
        List<AddressDto> addressDtos = new ArrayList<>();
        PersonDto personDto = personFeignService.postPerson(personResponseDto.getPerson());

        if (Objects.nonNull(personDto)) {
            phoneDtos = avaliablePhonesToSave(personResponseDto, personDto.getId());
            addressDtos = avaliableAddressesToSave(personResponseDto, personDto.getId());
        }

        return PersonResponseDto.builder()
                .person(personDto)
                .phones(phoneDtos)
                .addresses(addressDtos)
                .build();
    }

    private List<AddressDto> avaliableAddressesToSave(PersonResponseDto personResponseDto, Long personId) {
        if(Objects.nonNull(personResponseDto.getPhones()) && !personResponseDto.getPhones().isEmpty()) {
            personResponseDto.getAddresses().forEach(addressDto -> addressDto.setPersonId(personId));
            return addressFeignService.postAddress(personResponseDto.getAddresses());
        }
        return new ArrayList<>();
    }

    private List<PhoneDto> avaliablePhonesToSave(PersonResponseDto personResponseDto, Long personId) {
        if(Objects.nonNull(personResponseDto.getPhones()) && !personResponseDto.getPhones().isEmpty()) {
            personResponseDto.getPhones().forEach(phoneDto -> phoneDto.setPersonId(personId));
            return phoneFeignService.postPhone(personResponseDto.getPhones());
        }
        return new ArrayList<>();
    }

    public List<PersonDto> getPersonList() {
        return personFeignService.getPersonList();
    }

    public List<AddressDto> getAdressesByPersonId(Long personId) {
        return addressFeignService.getAddressByPersonId(personId);
    }

    public List<PhoneDto> getPhonesByPersonId(Long personId) {
        return phoneFeignService.getPhoneByPersonId(personId);
    }
}
