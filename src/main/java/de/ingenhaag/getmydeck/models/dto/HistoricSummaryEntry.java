package de.ingenhaag.getmydeck.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricSummaryEntry {

  private Long lastOrderSeconds;

  private Long elapsedSeconds;

  private String elapsedDuration;

  private OffsetDateTime lastOrderDate;

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

  public String getElapsedDuration() {
    return elapsedDuration;
  }

  public void setElapsedDuration(String elapsedDuration) {
    this.elapsedDuration = elapsedDuration;
  }

  public void setLastOrderDate(OffsetDateTime lastOrderDate) {
    this.lastOrderDate = lastOrderDate;
  }

  public OffsetDateTime getLastOrderDate() {
    return lastOrderDate;
  }
}
