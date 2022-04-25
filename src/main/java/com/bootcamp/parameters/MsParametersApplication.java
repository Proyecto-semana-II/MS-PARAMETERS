package com.bootcamp.parameters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsParametersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsParametersApplication.class, args);
	}

}
