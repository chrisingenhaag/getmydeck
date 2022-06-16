package de.ingenhaag.getmydeck.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Configuration
@ConfigurationProperties(prefix = "getmydeck.steam")
public class SteamConfiguration {

  private OffsetDateTime reservationStart;

  public OffsetDateTime getReservationStart() {
    return reservationStart;
  }

  public void setReservationStart(OffsetDateTime reservationStart) {
    this.reservationStart = reservationStart;
  }

}
