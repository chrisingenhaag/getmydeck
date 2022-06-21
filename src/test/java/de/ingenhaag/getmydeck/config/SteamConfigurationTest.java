package de.ingenhaag.getmydeck.config;

import de.ingenhaag.getmydeck.testsupport.DeckStaticTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SteamConfigurationTest extends DeckStaticTest {

  @Autowired
  SteamConfiguration configuration;

  @Test
  void testLoadCompleteConfig() {
    Assertions.assertNotNull(configuration.getReservationStart());
  }

}
