package de.ingenhaag.getmydeck.models.google;

import java.util.List;

public class DeckBotSheetResponse {

  private List<List<String>> values;

  public List<List<String>> getValues() {
    return values;
  }

  public void setValues(List<List<String>> values) {
    this.values = values;
  }

  @Override
  public String toString() {
    return "DeckBotSheetResponse{" +
        "values=" + values +
        '}';
  }
}
