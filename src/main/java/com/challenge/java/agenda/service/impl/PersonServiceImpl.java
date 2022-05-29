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
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final MessageSource messageSource;
    private static final int SIZE_DEFAULT = 10;

    @Override
    public PersonResponseDto create(PersonRequestDto personRequestDto) {
        Person person = new Person();
        PersonRequestDto prd = PersonServiceImpl.newPhone(personRequestDto);
        personMapper.mapDtoToEntity(person, prd);
        Person personSave = personRepository.save(person);
        return personMapper.mapEntityToDto(personSave);
    }

    private static PersonRequestDto newPhone(PersonRequestDto personRequestDto){
        PersonRequestDto person = new PersonRequestDto();
        person.setName(personRequestDto.getName());
        person.setPhone(PersonServiceImpl.convertPhone(personRequestDto.getPhone()));
        return person;
    }

    private static String convertPhone(String phone){
        String patron = "^(\\+\\d{1,3}( )?)[ ](\\d{3}[- ]){2}\\d{4}$";
        String patron2 = "^(\\+\\d{2}( )?)[ ](\\d{2}[ ]?)[ ](\\d{4})-(\\d{4})$";

        StringBuffer number = new StringBuffer(phone);

        if(phone.matches(patron) || phone.matches(patron2)) {
            return number.toString();
        }else if(phone.contains("+54341")){
            number.insert(3, " ");
            number.insert(7, " ");
            number.insert(11, "-");
            return number.toString();
        }
        else if(!phone.contains("+54") && phone.startsWith("341")) {
            number.insert(0, "+");
            number.insert(1, "5");
            number.insert(2, "4");
            number.insert(3, " ");
            number.insert(7, " ");
            number.insert(11, "-");
            return number.toString();
        }else if(!phone.contains("+54") && phone.startsWith("11")) {
            number.insert(0, "+");
            number.insert(1, "5");
            number.insert(2, "4");
            number.insert(3, " ");
            number.insert(6, " ");
            number.insert(11, "-");
            return number.toString();
        }
        else {
            number.insert(3, " ");
            number.insert(6, " ");
            number.insert(11, "-");
            return number.toString();
        }
    }

    @Override
    public PersonResponseDto findById(Long id) {
        String personNotFound = messageSource.getMessage("person.notFound", null, Locale.US);
        Person person = personRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(personNotFound));
        return personMapper.mapEntityToDto(person);
    }

    @Override
    public List<PersonResponseDto> getAll() {
        String personListIsEmpty = messageSource.getMessage("person.listEmpty", null, Locale.US);
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
            throw new EmptyDataException(personListIsEmpty);
        }
        return personResponse;
    }

    @Override
    public List<PersonResponseDto> getNumberByName(String name) {
        String personListIsEmpty = messageSource.getMessage("person.listEmpty", null, Locale.US);
        String personNotFound = messageSource.getMessage("person.notFound", null, Locale.US);
        List<PersonResponseDto> personResponse = new ArrayList();
        List<Person> existPerson = personRepository.findByName(name);
        if(!existPerson.isEmpty()){
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
                throw new EmptyDataException(personListIsEmpty);
            }
        }else{
            throw new NotFoundException(personNotFound);
        }
        return personResponse;
    }

    @Override
    public Page<Person> readAllPeople(Pageable pageable, int page) {
        String personListPageNotFound = messageSource.getMessage("person.pageNotFound", null, Locale.US);
        pageable = PageRequest.of(page, SIZE_DEFAULT);
        if (page >= personRepository.findAll(pageable).getTotalPages()) {
            throw new NotFoundException(personListPageNotFound);
        }
        return personRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        String personNotFound = messageSource.getMessage("person.notFound", null, Locale.US);
        String isDeletedPersonMessage = messageSource.getMessage("person.isDeleted", null, Locale.US);
        Person person = personRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(personNotFound));
        personRepository.delete(person);
        return new ResponseEntity<>(isDeletedPersonMessage, HttpStatus.OK);
    }
}
