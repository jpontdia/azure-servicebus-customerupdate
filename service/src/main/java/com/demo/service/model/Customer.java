package com.demo.service.model;

import com.demo.commons.constraints.NotEmptyString;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Customer {
    @NotEmptyString(message = "address must not be empty")
    @Schema(description = "address",  required = true)
    Address address;

    @NotEmptyString(message = "name must not be empty")
    @Schema(description = "name", required = true)
    Name name;
}
