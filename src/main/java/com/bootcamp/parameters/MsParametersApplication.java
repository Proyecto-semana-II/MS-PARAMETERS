package com.bootcamp.parameters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.bootcamp.parameters.controller", "com.bootcamp.parameters.service", "com.bootcamp.parameters.service.impl"})
public class MsParametersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsParametersApplication.class, args);
	}

}
