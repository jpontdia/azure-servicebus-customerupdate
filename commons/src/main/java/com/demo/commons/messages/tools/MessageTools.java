package com.demo.commons.messages.tools;

import com.demo.commons.messages.MessageEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.io.ClassPathResource;

import javax.validation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

@Slf4j
@SuppressWarnings("unused")
@UtilityClass
public class MessageTools {

    public static String buildTime;
    public static String commitId;
    public static String buildVersion;

    public static void verify(Object message){
        if (message instanceof MessageEvent){
            var factory = Validation.buildDefaultValidatorFactory();
            var validator = factory.getValidator();

            Set<ConstraintViolation<Object>> violations = validator.validate(message);
            if (!violations.isEmpty()) {
                log.error("Next violations found while sending the message to Azure Service Bus:");
                for (ConstraintViolation<Object> violation : violations) {
                    log.error("Constraint violation: {}", violation.getMessage());
                }
                throw new NestedRuntimeException(
                        "Error found while sending a message to the message broker, check server log for more details") {
                };
            }
        }
        else{
            throw new NestedRuntimeException(
                    "Incorrect message type sent to Azure Service Bus, only MessageEvent type is accepted") {
            };
        }
    }

    public static String generateId(){
        return UUID.randomUUID().toString();
    }

    public static String getBuildTime(){
        if (MessageTools.buildTime == null){
            getGitProperties();
        }
        return MessageTools.buildTime;
    }

    public static String getBuildVersion(){
        if (MessageTools.buildVersion == null){
            getGitProperties();
        }
        return MessageTools.buildVersion;
    }

    public static String getCommitId(){
        if (MessageTools.commitId == null){
            getGitProperties();
        }
        return MessageTools.commitId;
    }

    public static void getGitProperties (){
        File gitProperties ;
        try{
            gitProperties = new ClassPathResource("git.properties").getFile();
            parseGitProperties(gitProperties);
        }
        catch (Exception e){
            log.error("git.properties is not in the classpath, generate this resource using maven git-commit-id-maven-plugin", e);
        }
    }

    public static void parseGitProperties(File gitPropertiesFile){
        try (InputStream streamResource = new FileInputStream(gitPropertiesFile)) {
            var properties = new Properties();
            properties.load(streamResource);
            MessageTools.buildTime = properties.getProperty("git.build.time");
            MessageTools.buildVersion = properties.getProperty("git.build.version");
            MessageTools.commitId = properties.getProperty("git.commit.id.abbrev");
        }
        catch (Exception e){
            log.error("git.properties is not in the classpath, generate this resource using maven git-commit-id-maven-plugin", e);
        }
    }

    @SuppressWarnings({"java:S3740"})
    public static Object getMessage(String json, Class<?> objectType){
        Object result = null;
        try {
            var objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            result = objectMapper.readValue(json, objectType);
        }
        catch (IOException e) {
            log.error("Error when converting json to java class", e);
        }
        return result;
    }

    public static String getValue(Object object){
        var json = "";
        try {
            var objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }

        catch (IOException e) {
            log.error("Error when converting java to string", e);
        }
        return json;
    }
}
