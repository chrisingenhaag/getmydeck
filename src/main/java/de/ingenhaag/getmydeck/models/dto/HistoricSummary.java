package de.ingenhaag.getmydeck.models.dto;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;

import java.time.OffsetDateTime;
import java.util.SortedMap;

public class HistoricSummary {

  private OffsetDateTime lastUpdated;

  private SortedMap<Region, SortedMap<Version, HistoricSummarySet>> summary;

  public OffsetDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public SortedMap<Region, SortedMap<Version, HistoricSummarySet>> getSummary() {
    return summary;
  }

  public void setSummary(SortedMap<Region, SortedMap<Version, HistoricSummarySet>> summary) {
    this.summary = summary;
  }
}
