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
    return data.entrySet().stream().min(Map.Entry.comparingByKey()).map(entry -> {
          final DeckBotDataDaySet value = entry.getValue();
          DeckBotData deckBotData = new DeckBotData();
          deckBotData.setLastShipments(value.getLastShipments());
          return deckBotData;
        });
  }

  @JsonIgnore
  public void addOrUpdateNewDeckDataSet(DeckBotDataDaySet dataDaySet) {
    final LocalDate now = LocalDate.now(ZoneOffset.UTC);
    if(data == null) {
      data = new HashMap<LocalDate, DeckBotDataDaySet>();
    }

    if(data.containsKey(now)) {
      data.replace(now, dataDaySet);
    } else {
      data.put(now, dataDaySet);
    }
  }
}
