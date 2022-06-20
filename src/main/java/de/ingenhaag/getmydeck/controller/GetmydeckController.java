package de.ingenhaag.getmydeck.controller;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.config.validation.PlausibleDeckPreorderTime;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.services.DeckService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@Timed(histogram = true)
@Validated
public class GetmydeckController {

  @Autowired
  DeckService deckService;

  @Autowired
  SteamConfiguration config;

  @GetMapping("/api/v1/info")
  public InfoResponse getMydeckSetupInfo(
      @RequestParam("reserved") @Valid @PlausibleDeckPreorderTime OffsetDateTime reserved,
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
  public ResponseEntity<InfoResponse> getMydeckSetupInfoV2(
      @PathVariable("reserved") @Valid @PlausibleDeckPreorderTime OffsetDateTime reserved,
      @PathVariable("region") Region region,
      @PathVariable("version") Version version
  ) {
    return ResponseEntity.ok(deckService.getPersonalInfos(reserved, region, version));
  }

}
