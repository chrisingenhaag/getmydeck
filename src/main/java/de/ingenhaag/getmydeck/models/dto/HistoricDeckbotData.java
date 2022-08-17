package de.ingenhaag.getmydeck.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricDeckbotData {
  private LocalDate date;
  private Long elapsedSeconds;
  private Double elapsedTimePercentage;

  private Double increasedPercentage;

  private Long increasedSeconds;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Schema(
      nullable = true
  )
  @Nullable
  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }

  public void setElapsedSeconds(Long elapsedSeconds) {
    this.elapsedSeconds = elapsedSeconds;
  }

  @Schema(
      nullable = true
  )
  @Nullable
  public Double getElapsedTimePercentage() {
    return elapsedTimePercentage;
  }

  public void setElapsedTimePercentage(Double elapsedTimePercentage) {
    this.elapsedTimePercentage = elapsedTimePercentage;
  }

  @Schema(
      nullable = true
  )
  @Nullable
  public Double getIncreasedPercentage() {
    return increasedPercentage;
  }

  public void setIncreasedPercentage(Double increasedPercentage) {
    this.increasedPercentage = increasedPercentage;
  }

  public Long getIncreasedSeconds() {
    return increasedSeconds;
  }

  public void setIncreasedSeconds(Long increasedSeconds) {
    this.increasedSeconds = increasedSeconds;
  }

  @Override
  public String toString() {
    return "HistoricDeckbotData{" +
        "date=" + date +
        ", elapsedSeconds=" + elapsedSeconds +
        ", elapsedTimePercentage=" + elapsedTimePercentage +
        ", increasedPercentage=" + increasedPercentage +
        ", increasedSeconds=" + increasedSeconds +
        '}';
  }
}
