package com.challenge.java.agenda.dto;

import com.challenge.java.agenda.util.docs.PersonConstantDocs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ApiModel(value = PersonConstantDocs.PERSON_DTO_MODEL)
public class PersonRequestDto {

    @NotBlank(message = "The name of the person is required.")
    @Pattern(regexp = "^[A-Z]'?[- a-zA-Z]*$",message = "Name field must be a text string")
    @ApiModelProperty(notes = PersonConstantDocs.PERSON_DTO_MODEL_FIELD_NAME)
    private String name;
    @NotNull(message = "phone cannot be null")
    @ApiModelProperty(notes = PersonConstantDocs.PERSON_DTO_MODEL_FIELD_PHONE)
    private String phone;
}
