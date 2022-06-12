package de.ingenhaag.getmydeck.config;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;


@Configuration
@ConfigurationProperties(prefix = "steam")
public class SteamConfiguration {

  private OffsetDateTime reservationStart;
  private Map<Region, Map<Version, OffsetDateTime>> lastShipments;
  private OffsetDateTime lastStaticUpdate;

  public OffsetDateTime getDiffFrom() {
    return OffsetDateTime.now(ZoneOffset.UTC);
  }

  public OffsetDateTime getReservationStart() {
    return reservationStart;
  }

  public void setReservationStart(OffsetDateTime reservationStart) {
    this.reservationStart = reservationStart;
  }

  public Map<Region, Map<Version, OffsetDateTime>> getLastShipments() {
    return lastShipments;
  }

  public void setLastShipments(Map<Region, Map<Version, OffsetDateTime>> lastShipments) {
    this.lastShipments = lastShipments;
  }

  public OffsetDateTime getLastStaticUpdate() {
    return lastStaticUpdate;
  }

  public void setLastStaticUpdate(OffsetDateTime lastStaticUpdate) {
    this.lastStaticUpdate = lastStaticUpdate;
  }
}
