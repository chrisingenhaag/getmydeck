package de.ingenhaag.getmydeck.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricDeckbotData {
  private LocalDate date;
  private Long elapsedSeconds;
  private Double elapsedTimePercentage;

  private Double increasedPercentage;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }

  public void setElapsedSeconds(Long elapsedSeconds) {
    this.elapsedSeconds = elapsedSeconds;
  }

  public Double getElapsedTimePercentage() {
    return elapsedTimePercentage;
  }

  public void setElapsedTimePercentage(Double elapsedTimePercentage) {
    this.elapsedTimePercentage = elapsedTimePercentage;
  }

  public Double getIncreasedPercentage() {
    return increasedPercentage;
  }

  public void setIncreasedPercentage(Double increasedPercentage) {
    this.increasedPercentage = increasedPercentage;
  }

  @Override
  public String toString() {
    return "HistoricDeckbotData{" +
        "date=" + date +
        ", elapsedSeconds=" + elapsedSeconds +
        ", elapsedTimePercentage=" + elapsedTimePercentage +
        ", increasedPercentage=" + increasedPercentage +
        '}';
  }
}
