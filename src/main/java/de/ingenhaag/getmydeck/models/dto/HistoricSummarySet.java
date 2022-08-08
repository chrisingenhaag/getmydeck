package de.ingenhaag.getmydeck.models.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.SortedMap;

public class HistoricSummarySet implements Serializable {

  private SortedMap<LocalDate, HistoricSummaryEntry> entries;

  public SortedMap<LocalDate, HistoricSummaryEntry> getEntries() {
    return entries;
  }

  public void setEntries(SortedMap<LocalDate, HistoricSummaryEntry> entries) {
    this.entries = entries;
  }
}
