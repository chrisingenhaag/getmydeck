package de.ingenhaag.getmydeck.models.dto;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public class PersonalInfo {

  private OffsetDateTime reservedAt;
  private Region region;
  private Version version;

  private Duration durationReservedAfterStart;
  private String durationReservedAfterStartHumanReadable;
  private Double elapsedTimePercentage;
  private String prettyText;

  private String htmlText;
  private long latestOrderSeconds;
  private OffsetDateTime latestOrder;

  private OffsetDateTime lastDataUpdate;

  private LocalDate lastBatchDate;

  private List<HistoricDeckbotData> historicData;

  @Schema(
      example = "2021-07-23T06:40:37Z",
      description = "UTC time"
  )
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

  @Schema(
      type = "string",
      description = "String representation of Duration, not yet openapi standard",
      example = "PT157H40M37S"
  )
  public Duration getDurationReservedAfterStart() {
    return durationReservedAfterStart;
  }

  public void setDurationReservedAfterStartHumanReadable(String durationReservedAfterStartHumanReadable) {
    this.durationReservedAfterStartHumanReadable = durationReservedAfterStartHumanReadable;
  }

  @Schema(
      type = "string",
      description = "String representation of Duration, not yet openapi standard",
      example = "PT7H40M37S"
  )
  public String getDurationReservedAfterStartHumanReadable() {
    return durationReservedAfterStartHumanReadable;
  }

  public void setElapsedTimePercentage(Double elapsedTimePercentage) {
    this.elapsedTimePercentage = elapsedTimePercentage;
  }

  public Double getElapsedTimePercentage() {
    return elapsedTimePercentage;
  }

  public void setPrettyText(String prettyText) {
    this.prettyText = prettyText;
  }

  public String getPrettyText() {
    return prettyText;
  }

  public String getHtmlText() {
    return htmlText;
  }

  public void setHtmlText(String htmlText) {
    this.htmlText = htmlText;
  }

  public void setLatestOrderSeconds(long latestOrderSeconds) {
    this.latestOrderSeconds = latestOrderSeconds;
  }

  @Schema(
      example = "1626819802",
      format = "[0-9]{10}",
      minLength = 10,
      maxLength = 10,
      minimum = "1626454800",
      exclusiveMinimum = true
  )
  public long getLatestOrderSeconds() {
    return latestOrderSeconds;
  }

  public void setLatestOrder(OffsetDateTime latestOrder) {
    this.latestOrder = latestOrder;
  }

  @Schema(
      example = "2021-07-23T06:40:37Z",
      description = "UTC time"
  )
  public OffsetDateTime getLatestOrder() {
    return latestOrder;
  }

  public OffsetDateTime getLastDataUpdate() {
    return lastDataUpdate;
  }

  public void setLastDataUpdate(OffsetDateTime lastDataUpdate) {
    this.lastDataUpdate = lastDataUpdate;
  }

  @Schema(
      example = "2022-04-15"
  )
  public LocalDate getLastBatchDate() {
    return lastBatchDate;
  }

  public void setLastBatchDate(LocalDate lastBatchDate) {
    this.lastBatchDate = lastBatchDate;
  }

  public List<HistoricDeckbotData> getHistoricData() {
    return historicData;
  }

  public void setHistoricData(List<HistoricDeckbotData> historicData) {
    this.historicData = historicData;
  }

  @Override
  public String toString() {
    return "PersonalInfo{" +
        "reservedAt=" + reservedAt +
        ", region=" + region +
        ", version=" + version +
        ", durationReservedAfterStart=" + durationReservedAfterStart +
        ", durationReservedAfterStartHumanReadable='" + durationReservedAfterStartHumanReadable + '\'' +
        ", elapsedTimePercentage=" + elapsedTimePercentage +
        ", prettyText='" + prettyText + '\'' +
        ", htmlText='" + htmlText + '\'' +
        ", latestOrderSeconds=" + latestOrderSeconds +
        ", latestOrder=" + latestOrder +
        ", lastDataUpdate=" + lastDataUpdate +
        ", lastBatchDate=" + lastBatchDate +
        ", historicData=" + historicData +
        '}';
  }
}
