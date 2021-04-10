package com.yoti.hoovering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Start point of the application
 */
@SpringBootApplication
@EnableConfigurationProperties
public class HooveringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HooveringApplication.class, args);
	}

}
