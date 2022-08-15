package de.ingenhaag.getmydeck.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueDayEntry;
import de.ingenhaag.getmydeck.testsupport.DeckDataPersistenceBaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.*;
import java.util.Arrays;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckDataPersUpdateOnNewThursdayWithoutNewDataTest extends DeckDataPersistenceBaseTest {
  @Autowired
  private SteamDeckMongoService service;

  //Mock your clock bean
  @MockBean
  private Clock clock;

  //field that will contain the fixed clock
  private Clock fixedClock;

  @Test
  void updateDataOnNewThursday() throws JsonProcessingException {
    String stringUpdateDate = "2022-06-16"; // Thursday
    LocalDate updateDate = LocalDate.parse(stringUpdateDate);

    fixedClock = Clock.fixed(Instant.parse("2022-06-16T19:00:00.00Z"), ZoneOffset.UTC);
    when(clock.getZone()).thenReturn(ZoneOffset.UTC);
    when(clock.instant()).thenReturn(fixedClock.instant());

    // first the current thursday is missing
    SteamDeckQueueDayEntry latestData = service.getLatestData(Region.US, Version.S64);
    assertNotEquals(latestData.getDayOfBatch(), updateDate);

    final SortedMap<Region, SortedMap<Version, OffsetDateTime>> sampleData = getSampleData(stringUpdateDate);
    Arrays.stream(Region.values()).forEach(region -> {
      Arrays.stream(Version.values()).forEach(version -> {
        final Long latestOrder = service.getLatestData(region, version).getLatestOrder();
        sampleData.get(region).replace(version, OffsetDateTime.ofInstant(Instant.ofEpochSecond(latestOrder), ZoneOffset.UTC));
      });
    });

    service.updateParsedDataIfChanged(sampleData);

    // then the current thursday was added
    LocalDate expectedLastBatchDay = LocalDate.parse("2022-06-13");



    Arrays.stream(Region.values()).forEach(region -> {
      Arrays.stream(Version.values()).forEach(version -> {
        assertEquals(expectedLastBatchDay, service.getLatestData(region, version).getDayOfBatch());
      });
    });


  }
}