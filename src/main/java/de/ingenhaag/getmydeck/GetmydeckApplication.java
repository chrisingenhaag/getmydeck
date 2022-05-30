package de.ingenhaag.getmydeck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GetmydeckApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetmydeckApplication.class, args);
	}

}
