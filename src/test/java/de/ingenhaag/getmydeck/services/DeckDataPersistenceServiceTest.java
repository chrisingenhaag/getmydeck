package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
@TestPropertySource(properties = {
    "persistence.path=classpath://deckbot-data.json",
})
class DeckDataPersistenceServiceTest {
  @Autowired
  private DeckDataPersistenceService service;

  @Autowired
  private GoogleSheetScheduler scheduler;

  @Test
  void getDeckBotData() {
    scheduler.fetchDeckBotData();
    final DeckBotData deckBotData = service.getDeckBotData();
    assertTrue(deckBotData.isComplete());
  }
}