package com.springBoot.hospitalMngm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootHospitalmngSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHospitalmngSampleApplication.class, args);
		
	}

	@Bean
	public ModelMapper mapper() {
		
		return new ModelMapper();
	}

}
