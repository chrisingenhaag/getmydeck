package de.ingenhaag.getmydeck.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleSheetServiceTest {

  @Autowired
  GoogleSheetService service;

  @Test
  void getDeckBotData() {
    service.fetchDeckBotData();
    assertTrue(service.getDeckBotData().isComplete());
  }
}