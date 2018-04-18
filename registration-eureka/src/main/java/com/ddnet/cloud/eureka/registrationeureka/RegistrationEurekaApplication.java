package com.ddnet.cloud.eureka.registrationeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistrationEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationEurekaApplication.class, args);
	}
}
