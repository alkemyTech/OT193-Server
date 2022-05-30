package com.alkemy.somosmas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SomosMas API", version = "2.0", description = "Somos Más ONG"))
public class OngApplication {

	public static void main(String[] args) {SpringApplication.run(OngApplication.class, args);
	}

}
