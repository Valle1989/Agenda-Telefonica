package com.challenge.java.agenda.controller;

import com.challenge.java.agenda.assembler.PersonAssembler;
import com.challenge.java.agenda.dto.PersonRequestDto;
import com.challenge.java.agenda.dto.PersonResponseDto;
import com.challenge.java.agenda.model.Person;
import com.challenge.java.agenda.service.IPersonService;
import com.challenge.java.agenda.util.docs.PersonConstantDocs;
import io.swagger.annotations.*;
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
@Api(value = PersonConstantDocs.PERSON)
public class PersonController {

    private final IPersonService personService;
    private final PersonAssembler personAssembler;
    private final PagedResourcesAssembler<Person> pagedResourcesAssembler;

    @GetMapping("/{id}")
    @ApiOperation(value = PersonConstantDocs.PERSON_FIND_BY_ID, response = PersonResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = PersonConstantDocs.PERSON_GET_200_OK),
            @ApiResponse(code = 404, message = PersonConstantDocs.PERSON_404_NOT_FOUND)
    })
    public ResponseEntity<PersonResponseDto> findById(
            @ApiParam(value = PersonConstantDocs.PERSON_GET_PARAM_ID, required = true, example = "1")
            @Valid @PathVariable("id") Long id){
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = PersonConstantDocs.PERSON_ADD_PERSON, response = PersonResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = PersonConstantDocs.PERSON_POST_201_CREATED)
    })
    public ResponseEntity<PersonResponseDto> save(
            @ApiParam(value = PersonConstantDocs.PERSON_POST_PARAM_PERSON_REQUEST_DTO, required = true)
            @Valid @RequestBody PersonRequestDto personRequestDto){
        return new ResponseEntity<>(personService.create(personRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = PersonConstantDocs.PERSON_FIND_ALL, response = PersonResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = PersonConstantDocs.PERSON_GET_200_OK),
            @ApiResponse(code = 404, message = PersonConstantDocs.PERSON_GET_404_NOT_FOUND)
    })
    public ResponseEntity<List<PersonResponseDto>> findAll(){
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    @ApiOperation(value = PersonConstantDocs.PERSON_BY_NAME, response = PersonResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = PersonConstantDocs.PERSON_GET_200_OK),
            @ApiResponse(code = 404, message = PersonConstantDocs.PERSON_404_NOT_FOUND)
    })
    public ResponseEntity<List<PersonResponseDto>> getNumberByName(@Valid @RequestParam(value = "name") String name){
        return new ResponseEntity<>(personService.getNumberByName(name), HttpStatus.FOUND);
    }

    @GetMapping(params = "page")
    @ApiOperation(value = PersonConstantDocs.PERSON_READ_ALL_PEOPLE, response = PagedModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = PersonConstantDocs.PERSON_GET_200_OK),
            @ApiResponse(code = 404, message = PersonConstantDocs.PERSON_404_NOT_FOUND)
    })
    public ResponseEntity<PagedModel<PersonResponseDto>> findAllPeopleByName(
            @ApiParam(required = false) Pageable pageable,
            @ApiParam(value = PersonConstantDocs.PERSON_GET_PARAM_PAGE_NUMBER, required = true, example = "0") @RequestParam("page") int page){
        Page<Person> person = personService.readAllPeople(pageable, page);

        PagedModel<PersonResponseDto> personDtoModel = pagedResourcesAssembler
                .toModel(person, personAssembler);
        return new ResponseEntity<>(personDtoModel,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = PersonConstantDocs.PERSON_DELETE_PERSON, response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = PersonConstantDocs.PERSON_DELETE_200_OK),
            @ApiResponse(code = 403, message = PersonConstantDocs.PERSON_DELETE_403_FORBIDDEN),
            @ApiResponse(code = 404, message = PersonConstantDocs.PERSON_404_NOT_FOUND)
    })
    public ResponseEntity<?> deletePerson(
            @ApiParam(value = PersonConstantDocs.PERSON_DELETE_PARAM_ID, required = true, example = "1") @PathVariable("id") Long id){
        return new ResponseEntity<>(personService.delete(id), HttpStatus.OK);
    }
}
