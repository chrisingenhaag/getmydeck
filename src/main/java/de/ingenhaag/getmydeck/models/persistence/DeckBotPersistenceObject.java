package de.ingenhaag.getmydeck.models.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DeckBotPersistenceObject {

  private HashMap<LocalDate, DeckBotData> data;

  public HashMap<LocalDate, DeckBotData> getData() {
    return data;
  }

  public void setData(HashMap<LocalDate, DeckBotData> data) {
    this.data = data;
  }

  @JsonIgnore
  public Optional<DeckBotData> getLatestDeckData() {
    return data.entrySet().stream().min(Map.Entry.comparingByKey()).map(Map.Entry::getValue);
  }

  @JsonIgnore
  public void addOrUpdateNewDeckDataSet(DeckBotData dataDaySet) {
    final LocalDate now = LocalDate.now(ZoneOffset.UTC);
    if(data == null) {
      data = new HashMap<LocalDate, DeckBotData>();
    }

    if(data.containsKey(now)) {
      data.replace(now, dataDaySet);
    } else {
      data.put(now, dataDaySet);
    }
  }
}
