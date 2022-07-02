package de.ingenhaag.getmydeck.models.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class HistoricSummarySet implements Serializable {

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

  @Override
  public String toString() {
    return "HistoricSummarySet{" +
        "increaseTimeList=" + increaseTimeList +
        ", increaseDateList=" + increaseDateList +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HistoricSummarySet that = (HistoricSummarySet) o;
    return Objects.equals(increaseTimeList, that.increaseTimeList) && Objects.equals(increaseDateList, that.increaseDateList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(increaseTimeList, increaseDateList);
  }
}
