package com.alkemy.somosmas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SomosMas API", version = "3.0", description = "Employees Information"))
public class OngApplication {

	public static void main(String[] args) {SpringApplication.run(OngApplication.class, args);
	}

}
