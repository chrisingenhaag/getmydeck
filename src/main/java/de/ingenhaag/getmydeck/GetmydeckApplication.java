package de.ingenhaag.getmydeck;

import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Clock;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories
@EnableMongoAuditing
public class GetmydeckApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetmydeckApplication.class, args);
	}

	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET").allowedOrigins("*");
			}
		};
	}*/

}
