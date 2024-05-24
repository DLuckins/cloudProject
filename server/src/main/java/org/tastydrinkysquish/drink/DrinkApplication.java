package org.tastydrinkysquish.drink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan
public class DrinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrinkApplication.class, args);
	}

}