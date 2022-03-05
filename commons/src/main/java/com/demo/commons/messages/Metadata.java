package com.demo.commons.messages;

import com.demo.commons.constraints.NotEmptyString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {

    @NotNull(message= "messageType must not be empty")
    MessageType messageType;

    @NotEmpty(message="version number must not be empty")
    String version;

    @NotNull(message= "dateTime must not be empty")
    LocalDateTime dateTime;

    // Data related with the Code Repository of the publisher and commit labels
    @NotEmptyString(message="publisherCodeRepository number must not be empty")
    String publisherCodeRepository;

    @NotEmptyString(message="publisherCommitId number must not be empty")
    String publisherCommitId;

    @NotEmptyString(message="publisherBuildVersion number must not be empty")
    String publisherBuildVersion;

    @NotEmptyString(message="publisherBuildTime number must not be empty")
    String publisherBuildTime;
}