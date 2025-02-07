package com.learn.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LearingSpringBootApplication {


	public static void main(String[] args) {
		SpringApplication.run(LearingSpringBootApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
