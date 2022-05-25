package de.ingenhaag.getmydeck.controller;

import de.ingenhaag.getmydeck.models.InfoResponse;
import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
import de.ingenhaag.getmydeck.services.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
public class GetmydeckController {

  @Autowired
  DeckService deckService;

  @GetMapping("/api/v1/info")
  public InfoResponse getMydeckSetupInfo(
      @RequestParam("reserved") OffsetDateTime reserved,
      @RequestParam("region") Region region,
      @RequestParam("version") Version version
  ) {
    return deckService.getPersonalInfos(reserved, region, version);
  }

  @GetMapping("/api/v2/regions/{region}")
  public Map<Version, OffsetDateTime> getRegionInfo(
      @PathVariable("region") Region region
  ) {
    return deckService.getSelectedDeckRegion(region);
  }

  @GetMapping("/api/v2/regions/{region}/versions/{version}")
  public OffsetDateTime getRegionVersionInfo(
      @PathVariable("region") Region region,
      @PathVariable("version") Version version
  ) {
    return deckService.getSelectedDeckLastShipment(region, version);
  }
  @GetMapping("/api/v2/regions/{region}/versions/{version}/infos/{reserved}")
  public InfoResponse getMydeckSetupInfoV2(
      @PathVariable("reserved") OffsetDateTime reserved,
      @PathVariable("region") Region region,
      @PathVariable("version") Version version
  ) {
    return deckService.getPersonalInfos(reserved, region, version);
  }

}
