package com.mehmetgenc.secondhand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mehmetgenc.secondhand.user"})
public class SecondhandApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondhandApplication.class, args);
	}

}
