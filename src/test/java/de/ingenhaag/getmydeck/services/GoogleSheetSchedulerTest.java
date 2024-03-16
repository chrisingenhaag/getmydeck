package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.testsupport.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoogleSheetSchedulerTest extends AbstractIntegrationTest {

  @Autowired
  private SteamDeckMongoService service;

  @Autowired
  private GoogleSheetScheduler scheduler;


  //Mock your clock bean
  @MockBean
  private Clock clock;

  //field that will contain the fixed clock
  private Clock fixedClock;

  @Test
  void getDeckBotData() {
    fixedClock = Clock.fixed(Instant.parse("2022-08-08T19:00:00.00Z"), ZoneOffset.UTC);
    when(clock.getZone()).thenReturn(ZoneOffset.UTC);
    when(clock.instant()).thenReturn(fixedClock.instant());

    resetDataBase();

    Arrays.stream(Region.values()).forEach(region -> {
      Arrays.stream(Version.values()).forEach(version -> {
        assertEquals(service.getAllDataFromQueue(region, version).size(), 20);
      });
    });

    scheduler.fetchDeckBotData();

    Arrays.stream(Region.values()).forEach(region -> {
      Arrays.stream(Version.values()).forEach(version -> {
        assertEquals(service.getAllDataFromQueue(region, version).size(), 21);
      });
    });}
}