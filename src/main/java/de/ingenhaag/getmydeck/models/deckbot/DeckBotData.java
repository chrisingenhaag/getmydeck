package de.ingenhaag.getmydeck.models.deckbot;

import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
import de.ingenhaag.getmydeck.models.persistence.DeckBotDataDaySet;

import java.time.OffsetDateTime;
import java.util.Map;

public class DeckBotData extends DeckBotDataDaySet {

  private OffsetDateTime lastUpdated;

  private Map<Region, Map<Version, OffsetDateTime>> lastShipments;

  public OffsetDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

}
