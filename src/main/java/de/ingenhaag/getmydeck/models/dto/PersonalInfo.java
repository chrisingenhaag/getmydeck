package de.ingenhaag.getmydeck.models.dto;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;

import java.time.Duration;
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

  private List<HistoricDeckbotData> historicData;

  private PredictiveData predictiveData;

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

  public long getLatestOrderSeconds() {
    return latestOrderSeconds;
  }

  public void setLatestOrder(OffsetDateTime latestOrder) {
    this.latestOrder = latestOrder;
  }

  public OffsetDateTime getLatestOrder() {
    return latestOrder;
  }

  public List<HistoricDeckbotData> getHistoricData() {
    return historicData;
  }

  public void setHistoricData(List<HistoricDeckbotData> historicData) {
    this.historicData = historicData;
  }

  public PredictiveData getPredictiveData() {
    return predictiveData;
  }

  public void setPredictiveData(PredictiveData predictiveData) {
    this.predictiveData = predictiveData;
  }
}
