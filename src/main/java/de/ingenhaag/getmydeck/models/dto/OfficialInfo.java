package de.ingenhaag.getmydeck.models.dto;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;

public class OfficialInfo {

  private OffsetDateTime reservationsStartedAt;
  private OffsetDateTime lastDataUpdate;
  private LocalDate lastDataUpdateDate;
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

  public LocalDate getLastDataUpdateDate() {
    return lastDataUpdateDate;
  }

  public void setLastDataUpdateDate(LocalDate lastDataUpdateDate) {
    this.lastDataUpdateDate = lastDataUpdateDate;
  }

  @Override
  public String toString() {
    return "OfficialInfo{" +
        "reservationsStartedAt=" + reservationsStartedAt +
        ", lastDataUpdate=" + lastDataUpdate +
        ", lastDataUpdateDate=" + lastDataUpdateDate +
        ", lastShipments=" + lastShipments +
        '}';
  }
}
