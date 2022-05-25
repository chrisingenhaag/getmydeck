package de.ingenhaag.getmydeck.models;

import java.time.Duration;
import java.time.OffsetDateTime;

public class PersonalInfo {

  private OffsetDateTime reservedAt;
  private Region region;
  private Version version;
  private Duration durationReservedAfterStart;
  private String durationReservedAfterStartHumanReadable;
  private String elapsedTimePercentage;
  private String prettyText;

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

  public void setDurationReservedAfterStart(Duration durationReservedAfterStart) {
    this.durationReservedAfterStart = durationReservedAfterStart;
  }

  public Duration getDurationReservedAfterStart() {
    return durationReservedAfterStart;
  }

  public void setDurationReservedAfterStartHumanReadable(String durationReservedAfterStartHumanReadable) {
    this.durationReservedAfterStartHumanReadable = durationReservedAfterStartHumanReadable;
  }

  public String getDurationReservedAfterStartHumanReadable() {
    return durationReservedAfterStartHumanReadable;
  }

  public void setElapsedTimePercentage(String elapsedTimePercentage) {
    this.elapsedTimePercentage = elapsedTimePercentage;
  }

  public String getElapsedTimePercentage() {
    return elapsedTimePercentage;
  }

  public void setPrettyText(String prettyText) {
    this.prettyText = prettyText;
  }

  public String getPrettyText() {
    return prettyText;
  }
}
