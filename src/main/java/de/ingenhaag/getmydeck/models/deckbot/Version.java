package de.ingenhaag.getmydeck.models.deckbot;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Version {

  S64("64"),
  S256("256"),
  S512("512");

  public final String size;

  private Version(String size) {
    this.size = size;
  }

  @JsonValue
  public String getVersion() {
    return size;
  }
  public static Version valueOfSize(String size) {
    for (Version e : values()) {
      if (e.size.equals(size)) {
        return e;
      }
    }
    return null;
  }

}
