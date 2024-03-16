package de.ingenhaag.getmydeck.testsupport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;
import java.util.SortedMap;

@TestPropertySource(properties = {
    "getmydeck.deckbot.scheduler.enabled=false",
    "getmydeck.persistence.path=classpath:deckbot-data-persistence-tests.json",
})
public class DeckDataPersistenceBaseTest extends AbstractIntegrationTest {

  @Autowired
  ObjectMapper mapper;

  @BeforeEach
  void before() {
    resetDataBase();
  }

  public SortedMap<Region, SortedMap<Version, OffsetDateTime>> getSampleData(String date) throws JsonProcessingException {
    Double random = Math.random() * 10000;
    String timeStamp = String.valueOf(1626455555l + random.longValue());

    String data = """
      {
        "US" : {
          "64" : "replaceable",
          "256" : "replaceable",
          "512" : "replaceable"
        },
        "UK" : {
          "64" : "replaceable",
          "256" : "replaceable",
          "512" : "replaceable"
        },
        "EU" : {
          "64" : "replaceable",
          "256" : "replaceable",
          "512" : "replaceable"
        }
      }""";

    data = data.replace("replaceable", timeStamp);

    return mapper.readValue(data, new TypeReference<SortedMap<Region,SortedMap<Version, OffsetDateTime>>>() {});
  }

}
