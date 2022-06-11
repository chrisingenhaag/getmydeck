package de.ingenhaag.getmydeck.models.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;

public class DeckBotDataDaySet {

  private Map<Region, Map<Version, OffsetDateTime>> lastShipments;

  public Map<Region, Map<Version, OffsetDateTime>> getLastShipments() {
    return lastShipments;
  }

  public void setLastShipments(Map<Region, Map<Version, OffsetDateTime>> lastShipments) {
    this.lastShipments = lastShipments;
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
        "lastShipments=" + lastShipments +
        '}';
  }
}
