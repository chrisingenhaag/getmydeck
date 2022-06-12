package de.ingenhaag.getmydeck.models.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DeckBotPersistenceObject {

  private HashMap<LocalDate, DeckBotDataDaySet> data;

  public HashMap<LocalDate, DeckBotDataDaySet> getData() {
    return data;
  }

  public void setData(HashMap<LocalDate, DeckBotDataDaySet> data) {
    this.data = data;
  }

  @JsonIgnore
  public Optional<DeckBotData> getLatestDeckData() {
    return data.entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getValue).map(DeckBotData::of);
  }

  @JsonIgnore
  public void addOrUpdateNewDeckDataSet(DeckBotData deckBotData) {
    DeckBotDataDaySet dataDaySet = DeckBotDataDaySet.of(deckBotData);

    final LocalDate now = LocalDate.now(ZoneOffset.UTC);
    if(data == null) {
      data = new HashMap<>();
    }

    if(data.containsKey(now)) {
      data.replace(now, dataDaySet);
    } else {
      data.put(now, dataDaySet);
    }
  }
}
