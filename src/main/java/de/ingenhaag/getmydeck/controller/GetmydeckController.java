package de.ingenhaag.getmydeck.controller;

import de.ingenhaag.getmydeck.config.validation.PlausibleDeckPreorderTime;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.HistoricSummary;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import de.ingenhaag.getmydeck.services.DeckService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

  @Operation(
      parameters = {
          @Parameter(
              name = "reservationTimestamp",
              example = "1627022437",
              description = "Time in seconds from 1.1.1970",
              schema = @Schema(
                  format = "[0-9]{10}",
                  minLength = 10,
                  maxLength = 10,
                  minimum = "1626454800",
                  exclusiveMinimum = true
              )
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "400",
              description = "Bad request if reservationTimestamp is before official reservation start or in the future"
          )
      }
  )
  @GetMapping(
      path = "/api/v2/regions/{region}/versions/{version}/infos/{reservationTimestamp}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<InfoResponse> getPersonalInfoResponse(
      @PathVariable("reservationTimestamp") @Valid @PlausibleDeckPreorderTime OffsetDateTime reservationTimestamp,
      @PathVariable("region") Region region,
      @PathVariable("version") Version version
  ) {
    return ResponseEntity.ok(deckService.getPersonalInfos(reservationTimestamp, region, version));
  }

  @GetMapping(
      path = "/api/v2/summary",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<HistoricSummary> getHistoricSummary() {
    return ResponseEntity.ok(deckService.getHistoricSummary());
  }
}
