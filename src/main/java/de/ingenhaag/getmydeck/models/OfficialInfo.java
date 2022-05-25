package de.ingenhaag.getmydeck.models;

import java.time.OffsetDateTime;

public class OfficialInfo {

  private OffsetDateTime reservationsStartedAt;

  public OffsetDateTime getReservationsStartedAt() {
    return reservationsStartedAt;
  }

  public void setReservationsStartedAt(OffsetDateTime reservationsStartedAt) {
    this.reservationsStartedAt = reservationsStartedAt;
  }


}
