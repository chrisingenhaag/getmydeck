package de.ingenhaag.getmydeck.testsupport;


import com.github.tomakehurst.wiremock.WireMockServer;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueDayEntry;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueRepository;
import de.ingenhaag.getmydeck.services.support.DeckDataPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AbstractIntegrationTest {
  static final GenericContainer MONGO_DB_CONTAINER;

  static WireMockServer wireMockServer = new WireMockServer();

  static {
      MONGO_DB_CONTAINER =  new GenericContainer<>(DockerImageName.parse("bitnami/mongodb:4.4"))
              .withExposedPorts(27017)
              .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1))
              .withEnv(Map.of("MONGODB_ROOT_PASSWORD", "password123",
                      "MONGODB_USERNAME", "getmydeck",
                      "MONGODB_PASSWORD", "password123",
                      "MONGODB_DATABASE", "getmydeck"));
      MONGO_DB_CONTAINER.start();

      wireMockServer.start();
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

  @BeforeEach
  void beforeEach() {
    wireMockServer.resetAll();
    mockGoogleSheetResponse();
  }

  @Value("${getmydeck.deckbot.sheet-id}")
  private String googleSheetId;

  private void mockGoogleSheetResponse() {
    wireMockServer.stubFor(get(urlPathMatching("/"+googleSheetId+".*")).
        willReturn(aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withBody("""
            {
                "majorDimension": "ROWS",
                "range": "deckbot!A1:C9",
                "values": [
                    [
                        "64",
                        "US",
                        "1663832708"
                    ],
                    [
                        "256",
                        "US",
                        "1663858356"
                    ],
                    [
                        "512",
                        "US",
                        "1663902559"
                    ],
                    [
                        "64",
                        "UK",
                        "1664188785"
                    ],
                    [
                        "256",
                        "UK",
                        "1664132860"
                    ],
                    [
                        "512",
                        "UK",
                        "1664308210"
                    ],
                    [
                        "64",
                        "EU",
                        "1660596424"
                    ],
                    [
                        "256",
                        "EU",
                        "1664026789"
                    ],
                    [
                        "512",
                        "EU",
                        "1664298762"
                    ]
                ]
            }
                        
            """)));
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
        registry.add("getmydeck.deckbot.base-url", wireMockServer::baseUrl);
    }
}
