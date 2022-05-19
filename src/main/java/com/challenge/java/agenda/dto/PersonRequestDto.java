package com.challenge.java.agenda.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PersonRequestDto {

    @NotBlank(message = "The name of the person is required.")
    @Pattern(regexp = "^[A-Z]'?[- a-zA-Z]*$",message = "Name field must be a text string")
    private String name;
    @NotNull(message = "phone cannot be null")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)[ ](\\d{3}[- ]){2}\\d{4}$"
            + "|^(\\+\\d{2}( )?)[ ](\\d{2}[ ]?)[ ](\\d{4})-(\\d{4})$"
            , message = "Phone field must be a correct number. For example: +54 341 456-7890 or +54 11 6608-3366")
    private String phone;
}
