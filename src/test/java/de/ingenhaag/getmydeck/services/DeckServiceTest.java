package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "persistence.path=classpath:deckbot-data.json"
})
class DeckServiceTest {
  public static final String RESERVED_AT = "1627022437";
  @Autowired
  private DeckService service;

  @Test
  void testHistoricDataCalculation() {
    final InfoResponse personalInfos = service.getPersonalInfos(OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(RESERVED_AT)), ZoneOffset.UTC), Region.EU, Version.S512);
    assertEquals(7, personalInfos.getPersonalInfo().getHistoricData().size());
  }

}