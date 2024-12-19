package com.bridge.skill.usermanagement.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public Tika tika() {
        return new Tika();
    }
}
