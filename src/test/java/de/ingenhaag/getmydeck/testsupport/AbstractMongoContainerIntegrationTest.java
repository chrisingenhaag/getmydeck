package de.ingenhaag.getmydeck.testsupport;


import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueRepository;
import de.ingenhaag.getmydeck.services.SteamDeckMongoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Map;

@SpringBootTest
@Testcontainers
public class AbstractMongoContainerIntegrationTest {

  static final GenericContainer MONGODB_CONTAINER;

  static {
    MONGODB_CONTAINER = new GenericContainer<>(
        DockerImageName.parse("bitnami/mongodb:latest")
    )
        .withExposedPorts(27017)
        .withStartupTimeout(Duration.ofMinutes(3))
        .withEnv(Map.of("MONGODB_ROOT_PASSWORD", "password123",
            "MONGODB_USERNAME", "getmydeck",
            "MONGODB_PASSWORD", "password123",
            "MONGODB_DATABASE", "getmydeck"));
    MONGODB_CONTAINER.start();
  }

  @Autowired
  SteamDeckMongoService migrateDataToMongo;

  @Autowired
  SteamDeckQueueRepository repo;

  public void resetDataBase() {
    try {
      repo.deleteAll();
      migrateDataToMongo.migrateDataToMongo();
    } catch(Exception e) {
      e.printStackTrace();
    }

  }


  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri",
        () -> String.format("mongodb://getmydeck:password123@localhost:%d/getmydeck", MONGODB_CONTAINER.getFirstMappedPort()));
  }

}
