package de.ingenhaag.getmydeck.models.dto;

import java.time.LocalDate;

public class HistoricDeckbotData {
  private LocalDate date;
  private Double elapsedTimePercentage;

  private Double increasedPercentage;

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
        ", elapsedTimePercentage=" + elapsedTimePercentage +
        ", increasedPercentage=" + increasedPercentage +
        '}';
  }
}
