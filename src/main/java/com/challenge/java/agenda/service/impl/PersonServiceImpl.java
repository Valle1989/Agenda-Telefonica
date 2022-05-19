package com.challenge.java.agenda.service.impl;

import com.challenge.java.agenda.dto.PersonRequestDto;
import com.challenge.java.agenda.dto.PersonResponseDto;
import com.challenge.java.agenda.exception.EmptyDataException;
import com.challenge.java.agenda.exception.NotFoundException;
import com.challenge.java.agenda.mapper.PersonMapper;
import com.challenge.java.agenda.model.Person;
import com.challenge.java.agenda.repository.PersonRepository;
import com.challenge.java.agenda.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private static final int SIZE_DEFAULT = 10;

    @Override
    public PersonResponseDto create(PersonRequestDto personRequestDto) {
        Person person = new Person();
        personMapper.mapDtoToEntity(person, personRequestDto);
        Person personSave = personRepository.save(person);
        return personMapper.mapEntityToDto(personSave);
    }

    @Override
    public PersonResponseDto findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("notFoundCategoryMessage"));
        return personMapper.mapEntityToDto(person);
    }

    @Override
    public List<PersonResponseDto> getAll() {
        List<PersonResponseDto> personResponse = new ArrayList();
        personRepository.findAll()
                .stream()
                .forEach(person -> {
                    PersonResponseDto personResponseDto = new PersonResponseDto();
                    personResponseDto.setName(person.getName());
                    personResponseDto.setPhone(person.getPhone());
                    personResponse.add(personResponseDto);
                });
        if (personResponse.isEmpty()) {
            throw new EmptyDataException("persontListEmpty");
        }
        return personResponse;
    }

    @Override
    public List<PersonResponseDto> getNumberByName(String name) {
        List<PersonResponseDto> personResponse = new ArrayList();
        personRepository.findAll()
                .stream()
                .filter(person -> person.getName().toLowerCase().contains(name.toLowerCase()))
                .forEach(person -> {
                    PersonResponseDto personResponseDto = new PersonResponseDto();
                    personResponseDto.setName(person.getName());
                    personResponseDto.setPhone(person.getPhone());
                    personResponse.add(personResponseDto);
                });
        if (personResponse.isEmpty()) {
            throw new EmptyDataException("persontListEmpty");
        }
        return personResponse;
    }

    @Override
    public Page<Person> readAllPeople(Pageable pageable, int page) {
        pageable = PageRequest.of(page, SIZE_DEFAULT);
        if (page >= personRepository.findAll(pageable).getTotalPages()) {
            throw new NotFoundException("personListPageNotFound");
        }
        return personRepository.findAll(pageable);
    }
}
