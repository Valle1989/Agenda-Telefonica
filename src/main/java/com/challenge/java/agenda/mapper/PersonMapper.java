package com.challenge.java.agenda.mapper;

import com.challenge.java.agenda.dto.PersonRequestDto;
import com.challenge.java.agenda.dto.PersonResponseDto;
import com.challenge.java.agenda.model.Person;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class PersonMapper {

    public PersonResponseDto mapEntityToDto(Person person){
        PersonResponseDto personResponse = new PersonResponseDto();
        personResponse.setName(person.getName());
        personResponse.setPhone(person.getPhone());
        return personResponse;
    }

    public void mapDtoToEntity(Person person,@Valid PersonRequestDto personRequestDto){
        person.setName(personRequestDto.getName());
        person.setPhone(personRequestDto.getPhone());
    }
}
