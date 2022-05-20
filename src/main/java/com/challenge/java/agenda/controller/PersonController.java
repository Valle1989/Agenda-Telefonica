package com.challenge.java.agenda.controller;

import com.challenge.java.agenda.assembler.PersonAssembler;
import com.challenge.java.agenda.dto.PersonRequestDto;
import com.challenge.java.agenda.dto.PersonResponseDto;
import com.challenge.java.agenda.model.Person;
import com.challenge.java.agenda.service.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final IPersonService personService;
    private final PersonAssembler personAssembler;
    private final PagedResourcesAssembler<Person> pagedResourcesAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> findById(@Valid @PathVariable("id") Long id){
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PersonResponseDto> save(@Valid @RequestBody PersonRequestDto personRequestDto){
        return new ResponseEntity<>(personService.create(personRequestDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PersonResponseDto>> findAll(){
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<PersonResponseDto>> getNumberByName(@Valid @RequestParam(value = "name") String name){
        return new ResponseEntity<>(personService.getNumberByName(name), HttpStatus.FOUND);
    }

    @GetMapping(params = "page")
    public ResponseEntity<PagedModel<PersonResponseDto>> findAllPeopleByName(Pageable pageable, @RequestParam("page") int page){
        Page<Person> person = personService.readAllPeople(pageable, page);

        PagedModel<PersonResponseDto> personDtoModel = pagedResourcesAssembler
                .toModel(person, personAssembler);
        return new ResponseEntity<>(personDtoModel,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id){
        return new ResponseEntity<>(personService.delete(id), HttpStatus.OK);
    }
}
