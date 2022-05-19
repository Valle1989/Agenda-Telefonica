package com.challenge.java.agenda.service;

import com.challenge.java.agenda.dto.PersonRequestDto;
import com.challenge.java.agenda.dto.PersonResponseDto;
import com.challenge.java.agenda.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public interface IPersonService {

    PersonResponseDto create(@Valid PersonRequestDto personRequestDto);
    PersonResponseDto findById(Long id);
    List<PersonResponseDto> getAll();
    List<PersonResponseDto> getNumberByName(String name);
    Page<Person> readAllPeople(Pageable pageable, int page);
}
