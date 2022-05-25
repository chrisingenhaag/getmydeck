package de.ingenhaag.getmydeck.controller;

import de.ingenhaag.getmydeck.models.InfoResponse;
import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
import de.ingenhaag.getmydeck.services.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class GetmydeckController {

  @Autowired
  DeckService deckService;

  @GetMapping("/api/info")
  public InfoResponse getMydeckSetupInfo(
      @RequestParam("reserved") OffsetDateTime reserved,
      @RequestParam("region") Region region,
      @RequestParam("version") Version version
  ) {
    return deckService.getInfos(reserved, region, version);
  }

}
