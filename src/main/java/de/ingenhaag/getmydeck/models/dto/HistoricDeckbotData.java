package de.ingenhaag.getmydeck.models.dto;

import java.time.LocalDate;

public class HistoricDeckbotData {
  private LocalDate date;
  private String elapsedTimePercentage;
  private String elapsedTimePercentageIncrease;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getElapsedTimePercentage() {
    return elapsedTimePercentage;
  }

  public void setElapsedTimePercentage(String elapsedTimePercentage) {
    this.elapsedTimePercentage = elapsedTimePercentage;
  }

  public String getElapsedTimePercentageIncrease() {
    return elapsedTimePercentageIncrease;
  }

  public void setElapsedTimePercentageIncrease(String elapsedTimePercentageIncrease) {
    this.elapsedTimePercentageIncrease = elapsedTimePercentageIncrease;
  }

  @Override
  public String toString() {
    return "HistoricDeckbotData{" +
        "date=" + date +
        ", elapsedTimePercentage='" + elapsedTimePercentage + '\'' +
        ", elapsedTimePercentageIncrease='" + elapsedTimePercentageIncrease + '\'' +
        '}';
  }
}
