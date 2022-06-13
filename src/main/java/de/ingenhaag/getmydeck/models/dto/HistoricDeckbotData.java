package de.ingenhaag.getmydeck.models.dto;

import java.time.LocalDate;

public class HistoricDeckbotData {
  private LocalDate date;
  private Double elapsedTimePercentage;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Double getElapsedTimePercentage() {
    return elapsedTimePercentage;
  }

  public void setElapsedTimePercentage(Double elapsedTimePercentage) {
    this.elapsedTimePercentage = elapsedTimePercentage;
  }

  @Override
  public String toString() {
    return "HistoricDeckbotData{" +
        "date=" + date +
        ", elapsedTimePercentage=" + elapsedTimePercentage +
        '}';
  }
}
