package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;

@Service
public class DeckService {

  @Autowired
  SteamConfiguration config;

  public InfoResponse getInfos(OffsetDateTime reservedAt, Region region, Version version) {
    OfficialInfo officialInfo = new OfficialInfo();
    officialInfo.setReservationsStartedAt(config.getReservationStart());

    PersonalInfo personalInfo = new PersonalInfo();
    personalInfo.setRegion(region);
    personalInfo.setVersion(version);
    personalInfo.setReservedAt(reservedAt);
    personalInfo.setDurationReservedAfterStart(Duration.between(config.getReservationStart(), reservedAt));
    personalInfo.setDurationReservedAfterStartHumanReadable(humanReadableDuration(Duration.between(config.getReservationStart(), reservedAt)));
    personalInfo.setElapsedTimePercentage("foo");

    InfoResponse info = new InfoResponse();
    info.setOfficialInfo(officialInfo);
    info.setPersonalInfo(personalInfo);
    return info;
  }

  private String humanReadableDuration(Duration duration) {
    long d = duration.toDays();
    long HH = duration.toHoursPart();
    long MM = duration.toMinutesPart();
    long SS = duration.toSecondsPart();
    return String.format("%02d days %02d hours %02d minutes and %02d seconds", d, HH, MM, SS);
  }

}
