package de.ingenhaag.getmydeck;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class ConfigurationLoadTest {

  @Autowired
  SteamConfiguration configuration;

  @Test
  void testLoadCompleteConfig() {
    Assertions.assertNotNull(configuration.getReservationStart());
    Assertions.assertNotNull(configuration.getLastShipments(), "lastShipment list couldn´t be loaded from application.yml");
  }

  @ParameterizedTest
  @MethodSource("getEnumMatrix")
  void allLastShipmentsLoaded(Region region, Version version) {
    Assertions.assertNotNull(configuration.getLastShipments().get(region).get(version),String.format("config couldn´t be loaded from region %s and version %s", region, version));

  }

  private static Stream<Arguments> getEnumMatrix() {
    return Stream.of(
        Arguments.of(Region.EU, Version.S64),
        Arguments.of(Region.EU, Version.S256),
        Arguments.of(Region.EU, Version.S512),
        Arguments.of(Region.UK, Version.S64),
        Arguments.of(Region.UK, Version.S256),
        Arguments.of(Region.UK, Version.S512),
        Arguments.of(Region.US, Version.S64),
        Arguments.of(Region.US, Version.S256),
        Arguments.of(Region.US, Version.S512)
    );
  }
}
