package de.ingenhaag.getmydeck.models.dto;

import java.util.Objects;

public class InfoResponse {

  private OfficialInfo officialInfo;
  private PersonalInfo personalInfo;

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

  @Override
  public String toString() {
    return "InfoResponse{" +
        "officialInfo=" + officialInfo +
        ", personalInfo=" + personalInfo +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InfoResponse that = (InfoResponse) o;
    return Objects.equals(officialInfo, that.officialInfo) && Objects.equals(personalInfo, that.personalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(officialInfo, personalInfo);
  }
}
