package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Map;

@Service
public class DeckService {

  @Autowired
  SteamConfiguration config;

  public InfoResponse getPersonalInfos(OffsetDateTime reservedAt, Region region, Version version) {
    OfficialInfo officialInfo = new OfficialInfo();
    officialInfo.setReservationsStartedAt(config.getReservationStart());

    OffsetDateTime latestOrderSpecificVersion = getSelectedDeckLastShipment(region, version);

    System.out.println(latestOrderSpecificVersion.toEpochSecond());

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
            It looks like you have a %s %sGB reservation. You reserved your deck %s after pre-orders opened. 
            You're %s of the way there because the last orders with your configuration were %s (at reservation timestamp %s) before you did.""",
            region,
            version.getVersion(),
            personalInfo.getDurationReservedAfterStartHumanReadable(),
            personalInfo.getElapsedTimePercentage(),
            calculateDurationBetweenLastShipmentAndMyReservation(reservedAt, latestOrderSpecificVersion),
            personalInfo.getLatestOrderSeconds())
        );

    InfoResponse info = new InfoResponse();
    info.setOfficialInfo(officialInfo);
    info.setPersonalInfo(personalInfo);
    return info;
  }

  private Duration getDurationBetweenStartAndPersonalReservation(OffsetDateTime reservedAt) {
    return Duration.between(config.getReservationStart(), reservedAt);
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
    return config.getLastShipments().get(region).get(version);
  }

  public Map<Version, OffsetDateTime> getSelectedDeckRegion(Region region) {
    return config.getLastShipments().get(region);
  }
}
