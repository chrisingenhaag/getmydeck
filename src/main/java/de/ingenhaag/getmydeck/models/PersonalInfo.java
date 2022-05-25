package de.ingenhaag.getmydeck.models;

import java.time.OffsetDateTime;

public class PersonalInfo {

  private OffsetDateTime reservedAt;
  private Region region;
  private Version version;

  public OffsetDateTime getReservedAt() {
    return reservedAt;
  }
  public void setReservedAt(OffsetDateTime reservedAt) {
    this.reservedAt = reservedAt;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public Region getRegion() {
    return region;
  }

  public void setVersion(Version version) {
    this.version = version;
  }

  public Version getVersion() {
    return version;
  }
}
