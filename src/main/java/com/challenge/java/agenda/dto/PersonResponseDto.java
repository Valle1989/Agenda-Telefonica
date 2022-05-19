package com.challenge.java.agenda.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDto extends RepresentationModel<PersonResponseDto> {

    private String name;
    private String phone;
}
