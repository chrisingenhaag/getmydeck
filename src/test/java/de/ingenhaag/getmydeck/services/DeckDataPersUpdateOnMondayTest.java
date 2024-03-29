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
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckDataPersUpdateOnMondayTest extends DeckDataPersistenceBaseTest {
  @Autowired
  private SteamDeckMongoService service;

  //Mock your clock bean
  @MockBean
  private Clock clock;

  //field that will contain the fixed clock
  private Clock fixedClock;

  @Test
  void updateDataOnExistingMonday() throws JsonProcessingException {
    String instantExpected = "2022-06-13"; // Monday
    LocalDate dateExpected = LocalDate.parse(instantExpected);

    fixedClock = Clock.fixed(Instant.parse("2022-06-13T19:00:00.00Z"), ZoneOffset.UTC);
    when(clock.getZone()).thenReturn(ZoneOffset.UTC);
    when(clock.instant()).thenReturn(fixedClock.instant());

    final SortedMap<Region, SortedMap<Version, OffsetDateTime>> changedDataFromGoogle = getSampleData(instantExpected);
    service.updateParsedDataIfChanged(changedDataFromGoogle);

    final SteamDeckQueueDayEntry latestData = service.getLatestData(Region.EU, Version.S512);

    assertEquals(latestData.getDayOfBatch(), dateExpected);

    changedDataFromGoogle.forEach((region, versionOffsetDateTimeSortedMap) -> {
      versionOffsetDateTimeSortedMap.forEach((version, offsetDateTime) -> {
        assertEquals(service.getLatestData(region, version).getLatestOrder(), changedDataFromGoogle.get(region).get(version).toEpochSecond());
      });
    });
  }
}