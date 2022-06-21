package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.testsupport.DeckStaticTest;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeckDataPersistenceServiceTest extends DeckStaticTest {
  @Autowired
  private DeckDataPersistenceService service;

  @Test
  void getDeckBotData() {
    final DeckBotData deckBotData = service.getDeckBotData();
    assertTrue(deckBotData.isComplete());
  }
}