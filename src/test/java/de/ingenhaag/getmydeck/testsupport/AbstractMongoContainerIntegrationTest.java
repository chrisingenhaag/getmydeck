package de.ingenhaag.getmydeck.testsupport;


import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueDayEntry;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueRepository;
import de.ingenhaag.getmydeck.services.support.DeckDataPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AbstractMongoContainerIntegrationTest {
  static final GenericContainer MONGO_DB_CONTAINER;

  static {
      MONGO_DB_CONTAINER =  new GenericContainer<>(DockerImageName.parse("bitnami/mongodb:4.4"))
              .withExposedPorts(27017)
              .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1))
              .withEnv(Map.of("MONGODB_ROOT_PASSWORD", "password123",
                      "MONGODB_USERNAME", "getmydeck",
                      "MONGODB_PASSWORD", "password123",
                      "MONGODB_DATABASE", "getmydeck"));
      MONGO_DB_CONTAINER.start();
  }

  @Autowired
  SteamDeckQueueRepository repo;

  @Autowired
  DeckDataPersistenceService deckDataPersistenceService;

  public void resetDataBase() {
    try {
      repo.deleteAll();
      migrateDataToMongo();
    } catch(Exception e) {
      e.printStackTrace();
    }

  }

  public void deleteDataSet(LocalDate day, Region region, Version version) {
    repo.deleteByRegionAndVersionAndDayOfBatch(region,version,day);
  }

  private void migrateDataToMongo() {
    deckDataPersistenceService.getAllDataFromDisk().forEach((localDate, deckBotData) -> {
      final OffsetDateTime lastUpdated = deckBotData.getLastUpdated();
      deckBotData.getLastShipments().forEach((region, versionOffsetDateTimeSortedMap) ->
        versionOffsetDateTimeSortedMap.forEach((version, offsetDateTime) -> {
          SteamDeckQueueDayEntry entry = new SteamDeckQueueDayEntry();
          entry.setLastModified(lastUpdated.toLocalDateTime());
          entry.setRegion(region);
          entry.setVersion(version);
          entry.setDayOfBatch(localDate);
          entry.setLatestOrder(offsetDateTime.toEpochSecond());
          repo.save(entry);
        })
      );
    });
  }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                () -> String.format("mongodb://getmydeck:password123@%s:%d/getmydeck",
                        MONGO_DB_CONTAINER.getHost(),
                        MONGO_DB_CONTAINER.getFirstMappedPort()));
    }
}
