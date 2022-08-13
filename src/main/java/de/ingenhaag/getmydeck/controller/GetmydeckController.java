package de.ingenhaag.getmydeck.controller;

import de.ingenhaag.getmydeck.config.validation.PlausibleDeckPreorderTime;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.HistoricSummary;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import de.ingenhaag.getmydeck.services.DeckService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;

@OpenAPIDefinition(
    info = @Info(title = "GetMyDeck", version = "v2"),
    servers = {
        @Server(
            url = "https://getmydeck.ingenhaag.dev",
            description = "production"
        )
    }
)
@RestController
@Timed(histogram = true)
@Validated
public class GetmydeckController {

  @Autowired
  DeckService deckService;

  @GetMapping("/api/v2/regions/{region}/versions/{version}/infos/{reserved}")
  public ResponseEntity<InfoResponse> getPersonalInfoResponse(
      @PathVariable("reserved") @Valid @PlausibleDeckPreorderTime OffsetDateTime reserved,
      @PathVariable("region") Region region,
      @PathVariable("version") Version version
  ) {
    return ResponseEntity.ok(deckService.getPersonalInfos(reserved, region, version));
  }

  @GetMapping("/api/v2/summary")
  public ResponseEntity<HistoricSummary> getHistoricSummary() {
    return ResponseEntity.ok(deckService.getHistoricSummary());
  }
}
