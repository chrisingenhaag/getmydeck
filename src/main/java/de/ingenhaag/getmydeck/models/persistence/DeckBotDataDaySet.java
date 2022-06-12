package de.ingenhaag.getmydeck.models.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeckBotDataDaySet {

  private String lastUpdated;
  private Map<Region, Map<Version, String>> lastShipments;

  public static DeckBotDataDaySet of(DeckBotData deckBotData) {
    DeckBotDataDaySet data = new DeckBotDataDaySet();
    data.setLastUpdated(String.valueOf(deckBotData.getLastUpdated().toEpochSecond()));

    Map<Region, Map<Version, String>> resultMap = new HashMap<>();

    for(Region region : Region.values()) {
      Map<Version, String> regionMap = new HashMap<>();
      for(Version version : Version.values()) {
        regionMap.put(version, String.valueOf(deckBotData.getLastShipments().get(region).get(version).toEpochSecond()));
      }
      resultMap.put(region, regionMap);
    }
    data.setLastShipments(resultMap);
    return data;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public Map<Region, Map<Version, String>> getLastShipments() {
    return lastShipments;
  }

  public void setLastShipments(Map<Region, Map<Version, String>> lastShipments) {
    this.lastShipments = lastShipments;
  }

  public void setLastUpdated(String lastUpdated) {
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

  @Override
  public String toString() {
    return "DeckBotDataDaySet{" +
        "lastUpdated='" + lastUpdated + '\'' +
        ", lastShipments=" + lastShipments +
        '}';
  }
}
