package de.ingenhaag.getmydeck.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.ingenhaag.getmydeck.testsupport.DeckDataPersistenceBaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckDataPersUpdateOnNewThursdayTest extends DeckDataPersistenceBaseTest {
  @Autowired
  private DeckDataPersistenceService service;

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
    assertFalse(service.getAllDataFromDisk().containsKey(updateDate));

    service.updateParsedDataIfChanged(getSampleData(stringUpdateDate));

    // then the current thursday was added
    assertTrue(service.getAllDataFromDisk().containsKey(updateDate));

  }
}