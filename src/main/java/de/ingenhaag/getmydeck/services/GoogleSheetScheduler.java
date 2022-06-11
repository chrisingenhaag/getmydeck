package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.DeckBotConfiguration;
import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class GoogleSheetScheduler {

  private static final Logger log = LoggerFactory.getLogger(GoogleSheetScheduler.class);

  private final RestTemplate restTemplate;
  private final DeckBotConfiguration deckBotConfiguration;
  private DeckDataPersistenceService persistenceService;

  @Autowired
  public GoogleSheetScheduler(RestTemplateBuilder builder,
                              DeckBotConfiguration deckBotConfiguration,
                              DeckDataPersistenceService persistenceService) {
    this.restTemplate = builder.build();
    this.deckBotConfiguration = deckBotConfiguration;
    this.persistenceService = persistenceService;

  }

  @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1, initialDelay = 2)
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
        persistenceService.updateParsedDataIfChanged(parsedData);
      } catch(NullPointerException e) {
        log.error("Error parsing response from googlesheet", e);
      }
    }
  }
}
