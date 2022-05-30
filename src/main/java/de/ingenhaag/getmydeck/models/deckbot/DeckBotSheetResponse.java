package de.ingenhaag.getmydeck.models.deckbot;

import java.util.List;

public class DeckBotSheetResponse {

  private List<DeckBotData> values;


  public List<DeckBotData> getValues() {
    return values;
  }

  public void setValues(List<DeckBotData> values) {
    this.values = values;
  }

  @Override
  public String toString() {
    return "DeckBotSheetResponse{" +
        "values=" + values +
        '}';
  }
}
