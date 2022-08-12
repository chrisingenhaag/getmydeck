package de.ingenhaag.getmydeck.models.persistence.mongo;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@CompoundIndexes({
    @CompoundIndex(name = "region_version", def = "{'region' : 1, 'version': 1}"),
    @CompoundIndex(name = "region_version_day", def = "{'region' : 1, 'version': 1, 'dayOfBatch': 1}", unique = true)
})
public class SteamDeckQueueDayEntry {

  @Id
  private String id;

  private Region region;
  private Version version;
  private LocalDate dayOfBatch;

  private Long latestOrder;

  @LastModifiedDate
  private LocalDateTime lastModified;

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public Version getVersion() {
    return version;
  }

  public void setVersion(Version version) {
    this.version = version;
  }

  public LocalDate getDayOfBatch() {
    return dayOfBatch;
  }

  public void setDayOfBatch(LocalDate dayOfBatch) {
    this.dayOfBatch = dayOfBatch;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public Long getLatestOrder() {
    return latestOrder;
  }

  public void setLatestOrder(Long latestOrder) {
    this.latestOrder = latestOrder;
  }

  @Override
  public String toString() {
    return "SteamDeckQueueDayEntry{" +
        "id='" + id + '\'' +
        ", region=" + region +
        ", version=" + version +
        ", dayOfBatch=" + dayOfBatch +
        ", latestOrder=" + latestOrder +
        ", lastModified=" + lastModified +
        '}';
  }
}
