package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotSheetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Map;


@Service
public class GoogleSheetService {

  private RestTemplate restTemplate;

  @Autowired
  public GoogleSheetService(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
  }

  public Map<Region, Map<Version, OffsetDateTime>> getDeckBotData() {
    final ResponseEntity<DeckBotSheetResponse> forEntity = restTemplate.getForEntity(URI.create("https://sheets.googleapis.com/v4/spreadsheets/1ZaKncig9fce7K0sr1f-E2_sgLH1HuKQ-q3k7clPMOCs/values/deckbot!A1:C9?key=***REMOVED***"), DeckBotSheetResponse.class);
    if (forEntity.hasBody()) {
      System.out.println(forEntity.getBody());
    }

    return Collections.emptyMap();
  }
}
