package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.*;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;

@Service
public class DeckService {

  public static final String METRIC_DECKDATA_RETURNED = "deckdata_returned";
  public static final String METRIC_DECKDATA_TAG_OUTCOME = "outcome";
  public static final String METRIC_DECKDATA_COMPLETE = "complete_and_update";
  public static final String METRIC_DECKDATA_FALLBACK = "fallback_to_static";

  @Autowired
  private MeterRegistry meterRegistry;
  @Autowired
  private DeckDataPersistenceService deckDataPersistenceService;
  @Autowired
  private SteamConfiguration config;

  private final OfficialInfo officialInfo = new OfficialInfo();

  public InfoResponse getPersonalInfos(OffsetDateTime reservedAt, Region region, Version version) {
    this.officialInfo.setReservationsStartedAt(config.getReservationStart());

    OffsetDateTime latestOrderSpecificVersion = getSelectedDeckLastShipment(region, version);

    PersonalInfo personalInfo = new PersonalInfo();
    personalInfo.setRegion(region);
    personalInfo.setVersion(version);
    personalInfo.setLatestOrderSeconds(latestOrderSpecificVersion.toEpochSecond());
    personalInfo.setLatestOrder(latestOrderSpecificVersion);

    personalInfo.setReservedAt(reservedAt);
    personalInfo.setDurationReservedAfterStart(getDurationBetweenStartAndPersonalReservation(reservedAt));
    personalInfo.setDurationReservedAfterStartHumanReadable(humanReadableDuration(getDurationBetweenStartAndPersonalReservation(reservedAt)));
    personalInfo.setElapsedTimePercentage(calculateElapsedTimePercentage(reservedAt, latestOrderSpecificVersion));
    personalInfo.setPrettyText(
        String.format("""
            It looks like you have a %s %sGB reservation. 
            You reserved your deck %s after pre-orders opened. 
            %s of orders have been processed, and you have %s of orders to go until it is your turn. 
            You're %s of the way there!""",
            region,
            version.getVersion(),
            personalInfo.getDurationReservedAfterStartHumanReadable(),
            calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
            calculateDurationBetweenLastShipmentAndMyReservation(reservedAt, latestOrderSpecificVersion),
            personalInfo.getElapsedTimePercentage())
        );
    personalInfo.setHtmlText(
        String.format("""
            <ul>
              <li>It looks like you have a %s %sGB reservation</li>
              <li>You reserved your deck %s after pre-orders opened</li>
              <li>%s of orders have been processed</li>
              <li>You have %s of orders to go until it is your turn</li>
              <li>You're %s of the way there!</li>
            </ul>""",
            region,
            version.getVersion(),
            personalInfo.getDurationReservedAfterStartHumanReadable(),
            calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
            calculateDurationBetweenLastShipmentAndMyReservation(reservedAt, latestOrderSpecificVersion),
            personalInfo.getElapsedTimePercentage())
    );

    InfoResponse info = new InfoResponse();
    info.setOfficialInfo(officialInfo);
    info.setPersonalInfo(personalInfo);
    return info;
  }

  private Map<Region, Map<Version, OffsetDateTime>> getDeckBotDataOrDefault() {
    final DeckBotData deckBotData = deckDataPersistenceService.getDeckBotData();
    if (Objects.nonNull(deckBotData) && deckBotData.isComplete()) {
      meterRegistry.counter(METRIC_DECKDATA_RETURNED, METRIC_DECKDATA_TAG_OUTCOME, METRIC_DECKDATA_COMPLETE).increment();
      this.officialInfo.setLastDataUpdate(deckBotData.getLastUpdated());
      this.officialInfo.setLastShipments(deckBotData.getLastShipments());
      return deckBotData.getLastShipments();
    } else {
      meterRegistry.counter(METRIC_DECKDATA_RETURNED, METRIC_DECKDATA_TAG_OUTCOME, METRIC_DECKDATA_FALLBACK).increment();
      this.officialInfo.setLastDataUpdate(config.getLastStaticUpdate());
      this.officialInfo.setLastShipments(config.getLastShipments());
      return config.getLastShipments();
    }
  }

  private Duration getDurationBetweenStartAndPersonalReservation(OffsetDateTime reservedAt) {
    return Duration.between(config.getReservationStart(), reservedAt);
  }

  private String calculateDurationBetweenPreorderStartAndLastShipment(OffsetDateTime latestOrderSpecificVersion) {
    Duration d = Duration.between(config.getReservationStart(), latestOrderSpecificVersion);
    return humanReadableDuration(d);
  }

  private String calculateDurationBetweenLastShipmentAndMyReservation(OffsetDateTime reservedAt, OffsetDateTime latestOrderSpecificVersion) {
    Duration d = Duration.between(latestOrderSpecificVersion, reservedAt);
    return humanReadableDuration(d);
  }
  private String calculateElapsedTimePercentage(OffsetDateTime reservedAt, OffsetDateTime latestOrderSpecificVersion) {
    Duration diffToMyPersonalReservation = getDurationBetweenStartAndPersonalReservation(reservedAt);

    Duration diffToLastShipment = Duration.between(config.getReservationStart(), latestOrderSpecificVersion);

    Double percentage = (double) diffToLastShipment.getSeconds() * 100 / diffToMyPersonalReservation.getSeconds();
    return String.format("%1$,.2f %%", percentage);
  }

  private String humanReadableDuration(Duration duration) {
    long d = duration.toDays();
    long HH = duration.toHoursPart();
    long MM = duration.toMinutesPart();
    long SS = duration.toSecondsPart();
    return String.format("%02d days, %02d hours, %02d minutes and %02d seconds", d, HH, MM, SS);
  }

  public OffsetDateTime getSelectedDeckLastShipment(Region region, Version version) {
    return getDeckBotDataOrDefault().get(region).get(version);
  }

  public Map<Version, OffsetDateTime> getSelectedDeckRegion(Region region) {
    return getDeckBotDataOrDefault().get(region);
  }
}
