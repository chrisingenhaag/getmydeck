package de.ingenhaag.getmydeck.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.testsupport.DeckDataPersistenceBaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.*;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckDataPersUpdateOnTuesdayTest extends DeckDataPersistenceBaseTest {
  @Autowired
  private DeckDataPersistenceService service;

  //Mock your clock bean
  @MockBean
  private Clock clock;

  //field that will contain the fixed clock
  private Clock fixedClock;

  @Test
  void updateDataOnTuesday() throws JsonProcessingException {
    String stringUpdateDate = "2022-06-14"; // Tuesday
    LocalDate updateDate = LocalDate.parse(stringUpdateDate);

    fixedClock = Clock.fixed(Instant.parse("2022-06-14T19:00:00.00Z"), ZoneOffset.UTC);
    when(clock.getZone()).thenReturn(ZoneOffset.UTC);
    when(clock.instant()).thenReturn(fixedClock.instant());

    final SortedMap<Region, SortedMap<Version, OffsetDateTime>> changedDataFromGoogle = getSampleData(stringUpdateDate);
    service.updateParsedDataIfChanged(changedDataFromGoogle);

    assertFalse(service.getAllDataFromDisk().containsKey(updateDate));
  }

}