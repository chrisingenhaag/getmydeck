package de.ingenhaag.getmydeck.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "getmydeck.persistence.path=build/deckbot-data.json",
    "getmydeck.persistence.create-if-not-exists=true"
})
class GoogleSheetSchedulerTest {

  @Autowired
  private DeckDataPersistenceService service;

  @Autowired
  private GoogleSheetScheduler scheduler;

  @Test
  void getDeckBotData() {
    scheduler.fetchDeckBotData();
    assertTrue(service.getDeckBotData().isComplete());
  }
}