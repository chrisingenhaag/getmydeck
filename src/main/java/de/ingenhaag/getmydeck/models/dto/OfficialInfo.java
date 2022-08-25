package de.ingenhaag.getmydeck.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

public class OfficialInfo {
  private OffsetDateTime reservationsStartedAt;

  @Schema(
      example = "2021-07-16T17:00:00Z",
      description = "UTC time"
  )
  public OffsetDateTime getReservationsStartedAt() {
    return reservationsStartedAt;
  }

  public void setReservationsStartedAt(OffsetDateTime reservationsStartedAt) {
    this.reservationsStartedAt = reservationsStartedAt;
  }
  @Override
  public String toString() {
    return "OfficialInfo{" +
        "reservationsStartedAt=" + reservationsStartedAt +
        '}';
  }
}
