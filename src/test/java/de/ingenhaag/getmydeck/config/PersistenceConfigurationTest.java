package de.ingenhaag.getmydeck.config;

import de.ingenhaag.getmydeck.testsupport.DeckStaticTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class PersistenceConfigurationTest extends DeckStaticTest {

  @Autowired
  PersistenceConfiguration configuration;

  @Test
  void testLoadCompleteConfig() {
    Assertions.assertNotNull(configuration.getPath());
    Assertions.assertFalse(configuration.isCreateIfNotExists());
  }

}