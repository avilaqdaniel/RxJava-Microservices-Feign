package com.avilaq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiAtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAtmApplication.class, args);
	}

}
