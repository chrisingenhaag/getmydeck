package de.ingenhaag.getmydeck.models.dto;

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
}
