package de.ingenhaag.getmydeck.models.dto;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class PredictiveData {

  private LocalDate fiveShipmentAverage;

  public LocalDate getFiveShipmentAverage() {
    return fiveShipmentAverage;
  }

  public void setFiveShipmentAverage(LocalDate fiveShipmentAverage) {
    this.fiveShipmentAverage = fiveShipmentAverage;
  }

  @Override
  public String toString() {
    return "PredictiveData{" +
        "fiveShipmentAverage=" + fiveShipmentAverage +
        '}';
  }
}
