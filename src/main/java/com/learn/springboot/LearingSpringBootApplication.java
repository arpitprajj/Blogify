package com.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class LearingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearingSpringBootApplication.class, args);
	}

}
