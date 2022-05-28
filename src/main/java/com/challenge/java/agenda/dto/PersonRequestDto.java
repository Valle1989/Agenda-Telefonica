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
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)[ ](\\d{3}[- ]){2}\\d{4}$"
            + "|^(\\+\\d{2}( )?)[ ](\\d{2}[ ]?)[ ](\\d{4})-(\\d{4})$"
            , message = "Phone field must be a correct number. For example: +54 341 456-7890 or +54 11 6608-3366")
    @ApiModelProperty(notes = PersonConstantDocs.PERSON_DTO_MODEL_FIELD_PHONE)
    private String phone;
}
