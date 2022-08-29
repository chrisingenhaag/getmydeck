package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.services.support.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.services.support.DeckDataPersistenceService;
import de.ingenhaag.getmydeck.testsupport.DeckDataPersistenceBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckDataPersistenceServiceTest extends DeckDataPersistenceBaseTest {
  @Autowired
  private DeckDataPersistenceService service;

  @Test
  void getDeckBotData() {
    final DeckBotData deckBotData = service.getDeckBotData();
    assertTrue(deckBotData.isComplete());
  }
}