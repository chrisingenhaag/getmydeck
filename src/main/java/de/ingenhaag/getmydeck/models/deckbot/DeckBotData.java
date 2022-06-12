package de.ingenhaag.getmydeck.models.deckbot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.persistence.DeckBotDataDaySet;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeckBotData {

  private OffsetDateTime lastUpdated;
  private Map<Region, Map<Version, OffsetDateTime>> lastShipments;

  public OffsetDateTime getLastUpdated() {
    return lastUpdated;
  }

  public Map<Region, Map<Version, OffsetDateTime>> getLastShipments() {
    return lastShipments;
  }

  public void setLastShipments(Map<Region, Map<Version, OffsetDateTime>> lastShipments) {
    this.lastShipments = lastShipments;
  }

  public void setLastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @JsonIgnore
  public boolean isComplete() {
    return Objects.nonNull(lastShipments) &&
        lastShipments.size() == 3 &&
        lastShipments.get(Region.US).size() == 3 &&
        (lastShipments.get(Region.US).containsKey(Version.S64) &&
            lastShipments.get(Region.US).containsKey(Version.S256) &&
            lastShipments.get(Region.US).containsKey(Version.S512)) &&
        lastShipments.get(Region.UK).size() == 3 &&
        (lastShipments.get(Region.UK).containsKey(Version.S64) &&
            lastShipments.get(Region.UK).containsKey(Version.S256) &&
            lastShipments.get(Region.UK).containsKey(Version.S512)) &&
        lastShipments.get(Region.EU).size() == 3 &&
        (lastShipments.get(Region.EU).containsKey(Version.S64) &&
            lastShipments.get(Region.EU).containsKey(Version.S256) &&
            lastShipments.get(Region.EU).containsKey(Version.S512));
  }

  public static DeckBotData of(DeckBotDataDaySet daySet) {
    DeckBotData data = new DeckBotData();
    data.setLastUpdated(OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(daySet.getLastUpdated())), ZoneOffset.UTC));

    Map<Region, Map<Version, OffsetDateTime>> resultMap = new HashMap<>();

    for(Region region : Region.values()) {
      Map<Version, OffsetDateTime> regionMap = new HashMap<>();
      for(Version version : Version.values()) {
        OffsetDateTime dateTime = OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(daySet.getLastShipments().get(region).get(version))), ZoneOffset.UTC);
        regionMap.put(version, dateTime);
      }
      resultMap.put(region, regionMap);
    }
    data.setLastShipments(resultMap);
    return data;
  }

  @Override
  public String toString() {
    return "DeckBotData{" +
        "lastUpdated=" + lastUpdated +
        ", lastShipments=" + lastShipments +
        '}';
  }
}
