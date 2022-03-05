package com.demo.commons.messages.schema;

import com.demo.commons.messages.MessageEvent;
import com.demo.commons.constraints.NotEmptyString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressV1 extends MessageEvent {

    @NotEmptyString(message = "line1 must not be empty")
    @Size(min = 1, max = 50, message = ("line1 max size 50 characters"))
    private String line1;

    @Size(min = 0, max = 50, message = ("line2 max size 50 characters"))
    private String line2;

    @NotEmptyString(message = "City must not be empty")
    @Size(min = 1, max = 50, message = ("City max size 50 characters"))
    private String city;

    @NotEmptyString(message = "State must not be empty")
    private String state;

    @NotEmptyString(message = "Country must not be empty")
    @Pattern(regexp = "^US$|^CA", message = "Accepted values: US,CA")
    private String country;

    @NotEmptyString(message = "Zip must not be empty")
    @Pattern(regexp = "[0-9]{5}", message = "Zip size 5 numbers")
    private String zip;
}
