package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.DeckBotConfiguration;
import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotSheetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class GoogleSheetService {

  private static final Logger log = LoggerFactory.getLogger(GoogleSheetService.class);

  private DeckBotData deckBotData;
  private final RestTemplate restTemplate;
  private final DeckBotConfiguration deckBotConfiguration;

  @Autowired
  public GoogleSheetService(RestTemplateBuilder builder, DeckBotConfiguration deckBotConfiguration) {
    this.restTemplate = builder.build();
    this.deckBotConfiguration = deckBotConfiguration;
  }

  @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 15)
  protected void fetchDeckBotData() {
    final ResponseEntity<DeckBotSheetResponse> response = restTemplate.getForEntity(URI.create(deckBotConfiguration.getUrl()), DeckBotSheetResponse.class);
    if (response.hasBody()) {
      try {
        Map<Region, Map<Version, OffsetDateTime>> parsedData = new HashMap<>();
        response.getBody().getValues()
            .forEach(strings -> {
              Version version = Version.valueOfSize(strings.get(0));
              Region region = Region.valueOf(strings.get(1));
              OffsetDateTime stamp = OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(strings.get(2))), ZoneOffset.UTC);

              if (parsedData.containsKey(region)) {
                parsedData.get(region).put(version, stamp);
              } else {
                Map<Version, OffsetDateTime> versionOffsetDateTimeMap = new HashMap<>();
                versionOffsetDateTimeMap.put(version, stamp);
                parsedData.put(region, versionOffsetDateTimeMap);
              }
            });
        updateParsedDataIfChanged(parsedData);
      } catch(NullPointerException e) {
        log.error("Error parsing response from googlesheet", e);
      }
    }
  }

  private void updateParsedDataIfChanged(Map<Region, Map<Version, OffsetDateTime>> parsedData) {
    DeckBotData deckBotData = new DeckBotData();
    deckBotData.setLastShipments(parsedData);
    deckBotData.setLastUpdated(OffsetDateTime.now(ZoneOffset.UTC));
    if (deckBotData.isComplete() &&
        (this.deckBotData == null || !parsedData.entrySet().equals(this.deckBotData.getLastShipments().entrySet()))) {
      this.deckBotData = deckBotData;
      log.info("Success updating deckBotData to {}", this.deckBotData);
    } else {
      log.info("Data not changed on google sheet, skipping");
    }
  }

  public DeckBotData getDeckBotData() {
    return deckBotData;
  }
}
