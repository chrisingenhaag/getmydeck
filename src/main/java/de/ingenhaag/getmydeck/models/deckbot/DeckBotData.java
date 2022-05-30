package de.ingenhaag.getmydeck.models.deckbot;

import java.util.List;

public class DeckBotData {
  private List<List<String>> values;

  public List<List<String>> getValues() {
    return values;
  }

  public void setValues(List<List<String>> values) {
    this.values = values;
  }

  @Override
  public String toString() {
    return "DeckBotData{" +
        "values=" + values +
        '}';
  }
}
