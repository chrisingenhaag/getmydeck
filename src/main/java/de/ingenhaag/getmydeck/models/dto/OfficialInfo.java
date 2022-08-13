package de.ingenhaag.getmydeck.models.dto;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.SortedMap;

public class OfficialInfo {

  private OffsetDateTime reservationsStartedAt;

  @Schema(hidden = true)
  @Deprecated(forRemoval = true)
  private OffsetDateTime lastDataUpdate;

  @Schema(hidden = true)
  @Deprecated(forRemoval = true)
  private LocalDate lastDataUpdateDate;

  public OffsetDateTime getReservationsStartedAt() {
    return reservationsStartedAt;
  }

  public void setReservationsStartedAt(OffsetDateTime reservationsStartedAt) {
    this.reservationsStartedAt = reservationsStartedAt;
  }
  @Deprecated(forRemoval = true)
  public OffsetDateTime getLastDataUpdate() {
    return lastDataUpdate;
  }

  @Deprecated(forRemoval = true)
  public void setLastDataUpdate(OffsetDateTime lastDataUpdate) {
    this.lastDataUpdate = lastDataUpdate;
  }

  @Deprecated(forRemoval = true)
  public LocalDate getLastDataUpdateDate() {
    return lastDataUpdateDate;
  }

  @Deprecated(forRemoval = true)
  public void setLastDataUpdateDate(LocalDate lastDataUpdateDate) {
    this.lastDataUpdateDate = lastDataUpdateDate;
  }

  @Override
  public String toString() {
    return "OfficialInfo{" +
        "reservationsStartedAt=" + reservationsStartedAt +
        ", lastDataUpdate=" + lastDataUpdate +
        ", lastDataUpdateDate=" + lastDataUpdateDate +
        '}';
  }
}
