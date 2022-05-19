package com.challenge.java.agenda.assembler;

import com.challenge.java.agenda.controller.PersonController;
import com.challenge.java.agenda.dto.PersonResponseDto;
import com.challenge.java.agenda.model.Person;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class PersonAssembler extends RepresentationModelAssemblerSupport<Person, PersonResponseDto> {

    public PersonAssembler(){
        super(PersonController.class, PersonResponseDto.class);
    }

    @Override
    public PersonResponseDto toModel(Person person){

        PersonResponseDto personResponse = instantiateModel(person);

        personResponse.add(linkTo(
                methodOn(PersonController.class)
                        .findById(person.getId()))
                .withSelfRel());

        personResponse.setName(person.getName());
        personResponse.setPhone(person.getPhone());

        return personResponse;
    }
}
