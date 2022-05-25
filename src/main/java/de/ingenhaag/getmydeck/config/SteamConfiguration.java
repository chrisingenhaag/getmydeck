package de.ingenhaag.getmydeck.config;

import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;


@Configuration
@ConfigurationProperties(prefix = "steam")
public class SteamConfiguration {

  private int reservationStart;
  private Map<Region, Map<Version, Integer>> lastShipments;

  public int getReservationStart() {
    return reservationStart;
  }

  public void setReservationStart(int reservationStart) {
    this.reservationStart = reservationStart;
  }

  public OffsetDateTime getDiffFrom() {
    return OffsetDateTime.now(ZoneOffset.UTC);
  }


  public Map<Region, Map<Version, Integer>> getLastShipments() {
    return lastShipments;
  }

  public void setLastShipments(Map<Region, Map<Version, Integer>> lastShipments) {
    this.lastShipments = lastShipments;
  }
}
