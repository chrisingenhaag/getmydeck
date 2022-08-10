package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import de.ingenhaag.getmydeck.testsupport.DeckStaticTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckServiceTest extends DeckStaticTest {
  public static final String RESERVED_AT = "1627022437";
  public static final String RESERVATION_POSSIBLE = "1626522437";
  public static final String RESERVED_TOO_EARLY = "1626454799";
  @Autowired
  private DeckService service;

  @BeforeEach
  void before() {
    resetDataBase();
  }

  @Test
  void testHistoricDataCalculation() {
    final InfoResponse personalInfos = service.getPersonalInfos(OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(RESERVED_AT)), ZoneOffset.UTC), Region.EU, Version.S512);
    assertEquals(20, personalInfos.getPersonalInfo().getHistoricData().size());
  }

  @Test
  void reservationShouldbePossible() {
    final InfoResponse personalInfos = service.getPersonalInfos(OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(RESERVATION_POSSIBLE)), ZoneOffset.UTC), Region.EU, Version.S512);
    assertTrue(personalInfos.getPersonalInfo().getElapsedTimePercentage() > 100);
  }

}