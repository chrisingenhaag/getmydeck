package de.ingenhaag.getmydeck.models.dto;

import java.util.List;

public class HistoricSummarySet {

  private List<Long> increaseTimeList;

  private List<String> increaseDateList;

  public List<Long> getIncreaseTimeList() {
    return increaseTimeList;
  }

  public void setIncreaseTimeList(List<Long> increaseTimeList) {
    this.increaseTimeList = increaseTimeList;
  }

  public List<String> getIncreaseDateList() {
    return increaseDateList;
  }

  public void setIncreaseDateList(List<String> increaseDateList) {
    this.increaseDateList = increaseDateList;
  }
}
