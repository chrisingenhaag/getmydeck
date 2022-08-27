package de.ingenhaag.getmydeck.models.dto;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;

public class HistoricSummary implements Serializable {

  private SortedMap<Region, SortedMap<Version, HistoricSummarySet>> summary;
  private List<LocalDate> allDays;

  public SortedMap<Region, SortedMap<Version, HistoricSummarySet>> getSummary() {
    return summary;
  }

  public void setSummary(SortedMap<Region, SortedMap<Version, HistoricSummarySet>> summary) {
    this.summary = summary;
  }


  public void setAllDays(List<LocalDate> allDays) {
    this.allDays = allDays;
  }

  public List<LocalDate> getAllDays() {
    return allDays;
  }

  @Override
  public String toString() {
    return "HistoricSummary{" +
        "summary=" + summary +
        ", allDays=" + allDays +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HistoricSummary summary1 = (HistoricSummary) o;
    return Objects.equals(summary, summary1.summary) && Objects.equals(allDays, summary1.allDays);
  }

  @Override
  public int hashCode() {
    return Objects.hash(summary, allDays);
  }
}
