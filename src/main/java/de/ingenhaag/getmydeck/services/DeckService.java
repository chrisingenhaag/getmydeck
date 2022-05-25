package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class DeckService {

  @Autowired
  SteamConfiguration config;

  public InfoResponse getInfos(String reservedAt, Region region, Version version) {
    OfficialInfo officialInfo = new OfficialInfo();
    officialInfo.setReservationsStartedAt(OffsetDateTime.ofInstant(Instant.ofEpochSecond(config.getReservationStart()), ZoneOffset.UTC));

    OffsetDateTime reservedDate = OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(reservedAt)), ZoneOffset.UTC);
    PersonalInfo personalInfo = new PersonalInfo();
    personalInfo.setRegion(region);
    personalInfo.setVersion(version);
    personalInfo.setReservedAt(reservedDate);

    InfoResponse info = new InfoResponse();
    info.setOfficialInfo(officialInfo);
    info.setPersonalInfo(personalInfo);
    return info;
  }

}
