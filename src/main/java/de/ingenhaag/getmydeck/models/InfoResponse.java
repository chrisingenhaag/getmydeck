package de.ingenhaag.getmydeck.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;

public class InfoResponse {

  private OfficialInfo officialInfo;
  private PersonalInfo personalInfo;

  @JsonProperty("diff")
  public String reservationAfter() {
    return getDuration().toString();
  }

  @JsonProperty("diffHumanReadable")
  public String humanReadableReservationAfter() {
    Duration reservationDiff = getDuration();

    long d = reservationDiff.toDays();
    long HH = reservationDiff.toHoursPart();
    long MM = reservationDiff.toMinutesPart();
    long SS = reservationDiff.toSecondsPart();
    return String.format("%02d days %02d hours %02d minutes and %02d seconds", d, HH, MM, SS);
  }

  private Duration getDuration() {
    return Duration.between(officialInfo.getReservationsStartedAt(), personalInfo.getReservedAt());
  }

  public OfficialInfo getOfficialInfo() {
    return officialInfo;
  }

  public void setOfficialInfo(OfficialInfo officialInfo) {
    this.officialInfo = officialInfo;
  }

  public PersonalInfo getPersonalInfo() {
    return personalInfo;
  }

  public void setPersonalInfo(PersonalInfo personalInfo) {
    this.personalInfo = personalInfo;
  }
}
