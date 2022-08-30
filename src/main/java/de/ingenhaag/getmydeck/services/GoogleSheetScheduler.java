package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.DeckBotConfiguration;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.google.DeckBotSheetResponse;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@Service
@ConditionalOnProperty(prefix = "getmydeck.deckbot", value = "scheduler.enabled", havingValue = "true")
public class GoogleSheetScheduler {

  private static final Logger log = LoggerFactory.getLogger(GoogleSheetScheduler.class);

  private final RestTemplate restTemplate;
  private final DeckBotConfiguration deckBotConfiguration;
  private SteamDeckMongoService mongoPersistenceService;
  private MeterRegistry meterRegistry;

  @Autowired
  public GoogleSheetScheduler(RestTemplateBuilder builder,
                              DeckBotConfiguration deckBotConfiguration,
                              SteamDeckMongoService mongoPersistenceService,
                              MeterRegistry meterRegistry) {
    this.restTemplate = builder.build();
    this.deckBotConfiguration = deckBotConfiguration;
    this.mongoPersistenceService = mongoPersistenceService;
    this.meterRegistry = meterRegistry;
  }

  @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1, initialDelay = 2)
  protected void fetchDeckBotData() {
    try {
      final ResponseEntity<DeckBotSheetResponse> response = restTemplate.getForEntity(URI.create(deckBotConfiguration.getUrl()), DeckBotSheetResponse.class);
      if (response.hasBody() && response.getBody() != null) {
        try {
          SortedMap<Region, SortedMap<Version, OffsetDateTime>> parsedData = new TreeMap<>();
          response.getBody().getValues()
              .forEach(strings -> {
                Version version = Version.valueOfSize(strings.get(0));
                Region region = Region.valueOf(strings.get(1));
                OffsetDateTime stamp = OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(strings.get(2))), ZoneOffset.UTC);

                if (parsedData.containsKey(region)) {
                  parsedData.get(region).put(version, stamp);
                } else {
                  SortedMap<Version, OffsetDateTime> versionOffsetDateTimeMap = new TreeMap<>();
                  versionOffsetDateTimeMap.put(version, stamp);
                  parsedData.put(region, versionOffsetDateTimeMap);
                }
              });
          mongoPersistenceService.updateParsedDataIfChanged(parsedData);
          meterRegistry.counter("getmydeck.scheduler.executions", "outcome", "SUCCESS", "exception", "NONE").increment();
        } catch(NullPointerException e) {
          log.error("Error parsing response from googlesheet", e);
          meterRegistry.counter("getmydeck.scheduler.executions", "outcome", "FAILED", "exception", e.getCause().toString()).increment();
        }
      } else {
        log.error("Google sheet response evaluated to null with response {}", response);
        meterRegistry.counter("getmydeck.scheduler.executions", "outcome", "FAILED", "exception", "NONE").increment();
      }
    } catch (RestClientException exception) {
      log.error("RestClientException during google sheet sync scheduling", exception);
      meterRegistry.counter("getmydeck.scheduler.executions", "outcome", "FAILED", "exception", exception.getCause().toString()).increment();
    }
  }
}
