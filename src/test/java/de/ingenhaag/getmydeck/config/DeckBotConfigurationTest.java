package de.ingenhaag.getmydeck.config;

import de.ingenhaag.getmydeck.testsupport.DeckStaticTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeckBotConfigurationTest extends DeckStaticTest {

  @Autowired
  DeckBotConfiguration configuration;

  @Test
  void getUrl() {
    Assertions.assertNotNull(configuration.getUrl());
  }
}