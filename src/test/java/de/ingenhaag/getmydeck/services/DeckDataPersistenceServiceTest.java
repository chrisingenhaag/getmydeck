package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeckDataPersistenceServiceTest {
  @Autowired
  private DeckDataPersistenceService service;

  @Test
  void getDeckBotData() {
    final DeckBotData deckBotData = service.getDeckBotData();
    assertTrue(deckBotData.isComplete());
  }
}