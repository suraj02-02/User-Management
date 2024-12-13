package com.bridge.skill.usermanagement.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.bridge.skill.usermanagement.constants.UserConstants.DATE_PATTERN;

@Configuration
public class ObjectMapperConfig {

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {

        return new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .registerModule((new SimpleModule()).addSerializer(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN))))
            .registerModule((new SimpleModule()).addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN))))
            .registerModule((new SimpleModule()).addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_PATTERN))))
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setDateFormat(new SimpleDateFormat(DATE_PATTERN));
    }

}
