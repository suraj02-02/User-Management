package com.bridge.skill.usermanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
	   // log.info("Booting Up User Management Application......");
		SpringApplication.run(UserManagementApplication.class, args);
	}
}
