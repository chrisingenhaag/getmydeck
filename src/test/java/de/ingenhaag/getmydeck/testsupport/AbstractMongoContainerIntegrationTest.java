package de.ingenhaag.getmydeck.testsupport;


import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueDayEntry;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueRepository;
import de.ingenhaag.getmydeck.services.support.DeckDataPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public class AbstractMongoContainerIntegrationTest {

  @Container
  @ServiceConnection
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

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
}
