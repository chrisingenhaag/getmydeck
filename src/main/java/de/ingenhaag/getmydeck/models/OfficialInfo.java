package de.ingenhaag.getmydeck.models;

import java.time.OffsetDateTime;
import java.util.Map;

public class OfficialInfo {

  private OffsetDateTime reservationsStartedAt;
  private OffsetDateTime lastDataUpdate;
  private Map<Region, Map<Version, OffsetDateTime>> lastShipments;

  public OffsetDateTime getReservationsStartedAt() {
    return reservationsStartedAt;
  }

  public void setReservationsStartedAt(OffsetDateTime reservationsStartedAt) {
    this.reservationsStartedAt = reservationsStartedAt;
  }

  public OffsetDateTime getLastDataUpdate() {
    return lastDataUpdate;
  }

  public void setLastDataUpdate(OffsetDateTime lastDataUpdate) {
    this.lastDataUpdate = lastDataUpdate;
  }

  public void setLastShipments(Map<Region, Map<Version, OffsetDateTime>> lastShipments) {
    this.lastShipments = lastShipments;
  }

  public Map<Region, Map<Version, OffsetDateTime>> getLastShipments() {
    return lastShipments;
  }
}
