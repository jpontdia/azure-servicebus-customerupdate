package com.demo.service.model;

import com.demo.commons.constraints.NotEmptyString;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class Address {

    @NotEmptyString(message = "line1 must not be empty")
    @Size(min = 1, max = 50, message = ("line1 max size 50 characters"))
    @Schema(description = "Address line1", example = "1234 My Street", required = true)
    private String line1;

    @Size(min = 0, max = 50, message = ("line2 max size 50 characters"))
    @Schema(description = "Address line2", example = "Apt 4134")
    private String line2;

    @NotEmptyString(message = "City must not be empty")
    @Size(min = 1, max = 50, message = ("City max size 50 characters"))
    @Schema(description = "City", example = "Fort Collins", required = true)
    private String city;

    @NotEmptyString(message = "State must not be empty")
    @Schema(description = "State", example = "CT", required = true)
    private String state;

    @NotEmptyString(message = "Country must not be empty")
    @Schema(description = "US (United States), CA (Canada)",
            example = "US", allowableValues = {"US", "CA"}, required = true)
    @Pattern(regexp = "^US$|^CA", message = "Accepted values: US,CA")
    private String country;

    @NotEmptyString(message = "Zip must not be empty")
    @Schema(description = "Zip", example = "92345", required = true)
    @Pattern(regexp = "[0-9]{5}", message = "Zip size 5 numbers")
    private String zip;
}
