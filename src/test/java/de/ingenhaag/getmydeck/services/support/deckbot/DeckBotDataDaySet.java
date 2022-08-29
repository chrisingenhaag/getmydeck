package de.ingenhaag.getmydeck.services.support.deckbot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class DeckBotDataDaySet {

  private String lastUpdated;
  private TreeMap<Region, Map<Version, String>> lastShipments;

  public static DeckBotDataDaySet of(DeckBotData deckBotData) {
    DeckBotDataDaySet data = new DeckBotDataDaySet();
    data.setLastUpdated(String.valueOf(deckBotData.getLastUpdated().toEpochSecond()));

    TreeMap<Region, Map<Version, String>> resultMap = new TreeMap<>();

    for(Region region : Region.values()) {
      TreeMap<Version, String> regionMap = new TreeMap<>();
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

  public void setLastShipments(TreeMap<Region, Map<Version, String>> lastShipments) {
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
