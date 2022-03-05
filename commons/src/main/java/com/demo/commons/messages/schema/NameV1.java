package com.demo.commons.messages.schema;

import com.demo.commons.constraints.NotEmptyString;
import com.demo.commons.messages.MessageEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NameV1 extends MessageEvent {

    @Size(max = 20, message = "surTitle max size 20")
    private String surTitle;

    @Size(min=1, max = 50,message=("firstName max size 50"))
    @NotEmptyString(message = "firstName must not be empty")
    @Pattern(regexp = "^[a-zA-Z \\-\\.\\']*$", message = "firstName doesn't have a valid value")
    private String firstName;

    @Size(min=1, max = 50,message=("lastName max size 50"))
    @NotEmptyString(message = "lastName must not be empty")
    @Pattern(regexp = "^[a-zA-Z \\-\\.\\']*$", message = "lastName doesn't have a valid value")
    private String lastName;

    @Size(min=0, max = 50,message=("middleName max size 50"))
    @Pattern(regexp = "^[a-zA-Z \\-\\.\\']*$", message = "middleName doesn't have a valid value")
    private String middleName;

    @Size(max = 20, message = "suffix max size 20")
    private String suffix;

    @Size(max = 10, min = 10, message = "birthDate length must be 10")
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$",message="birthDate must be have format YYYY-MM-DD")
    private String birthDate;
}
