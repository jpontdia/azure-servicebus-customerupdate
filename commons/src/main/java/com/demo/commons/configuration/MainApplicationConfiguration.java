package com.demo.commons.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Configuration
@Slf4j
//@DependsOn({"buildProperties"})
@Order(0)
@SuppressWarnings("unused")
public class MainApplicationConfiguration {

    @Bean
    public String applicationVersion(BuildProperties buildProperties) {
        var version = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                .withLocale( Locale.US )
                .withZone( ZoneId.systemDefault() );
        if (buildProperties != null){
            var componentVersion = buildProperties.getArtifact()
                    + ":" + buildProperties.getVersion()
                    + ", " + formatter.format(buildProperties.getTime());
            version = "App Version: " + componentVersion;
            log.info("* {}", version);
        }
        return version;
    }
}
