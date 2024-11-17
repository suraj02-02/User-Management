package com.bridge.skill.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a bean configuration class for executor service.
 * Any type of executor service which we will be required can be configured here
 */

@Configuration
public class ExecutorManagerConfig {

      @Bean(name = "virtualThreadPerTaskExecutor")
      public ExecutorService taskExecutor(){
          return Executors.newVirtualThreadPerTaskExecutor();
      }

}
