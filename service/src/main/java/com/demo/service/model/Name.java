package com.demo.service.model;

import com.demo.commons.constraints.NotEmptyString;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Name {

    @Size(max = 20, message = "surTitle max size 20")
    @Schema(description = "surTitle", example = "Mr", maxLength = 20)
    private String surTitle;

    @Size(min=1, max = 50,message=("firstName max size 50"))
    @NotEmptyString(message = "firstName must not be empty")
    @Schema(description = "firstName", example= "Joe")
    @Pattern(regexp = "^[a-zA-Z \\-\\.\\']*$", message = "firstName doesn't have a valid value")
    private String firstName;

    @Size(min=1, max = 50,message=("lastName max size 50"))
    @NotEmptyString(message = "lastName must not be empty")
    @Schema(description = "LastName", example= "Doe")
    @Pattern(regexp = "^[a-zA-Z \\-\\.\\']*$", message = "lastName doesn't have a valid value")
    private String lastName;
  
    @Size(min=0, max = 50,message=("middleName max size 50"))
    @Schema(description = "MiddleName", example= "ABC")
    @Pattern(regexp = "^[a-zA-Z \\-\\.\\']*$", message = "middleName doesn't have a valid value")
    private String middleName;

    @Size(max = 20, message = "suffix max size 20")
    @Schema(description = "suffix", example = "Jr.", maxLength = 20)
    private String suffix;

    @Size(max = 10, min = 10, message = "birthDate length must be 10")
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$",message="birthDate must be have format YYYY-MM-DD")
    @Schema(description = "birthDate", type = "string", example = "1990-10-02", minLength = 10, maxLength = 10)
    private String birthDate;

}
