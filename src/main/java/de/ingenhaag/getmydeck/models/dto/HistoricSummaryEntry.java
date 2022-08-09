package de.ingenhaag.getmydeck.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricSummaryEntry {

  private Long lastOrderSeconds;

  private Long elapsedSeconds;

  private OffsetDateTime lastUpdate;

  public Long getLastOrderSeconds() {
    return lastOrderSeconds;
  }

  public void setLastOrderSeconds(Long lastOrderSeconds) {
    this.lastOrderSeconds = lastOrderSeconds;
  }

  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }

  public void setElapsedSeconds(Long elapsedSeconds) {
    this.elapsedSeconds = elapsedSeconds;
  }

  public OffsetDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}
