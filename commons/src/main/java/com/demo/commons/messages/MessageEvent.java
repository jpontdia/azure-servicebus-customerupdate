package com.demo.commons.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class MessageEvent {
    @NotNull
    @Valid
    Metadata metadata;
}
