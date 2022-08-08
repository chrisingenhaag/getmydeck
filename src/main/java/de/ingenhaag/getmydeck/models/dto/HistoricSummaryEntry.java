package de.ingenhaag.getmydeck.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricSummaryEntry {

  private Long lastOrderSeconds;

  private Long elapsedSeconds;

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
}
