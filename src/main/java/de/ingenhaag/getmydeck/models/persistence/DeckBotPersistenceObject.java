package de.ingenhaag.getmydeck.models.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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
  public Map<LocalDate, DeckBotData> getAllDeckData() {
    return data.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(localDateDeckBotDataDaySetEntry -> Map.entry(localDateDeckBotDataDaySetEntry.getKey(), DeckBotData.of(localDateDeckBotDataDaySetEntry.getValue())))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
      if(now.getDayOfWeek().equals(DayOfWeek.MONDAY) || now.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
        data.put(now, dataDaySet);
      } else {
        // search existing day in dataset
        LocalDate tempDate = now.minus(1, ChronoUnit.DAYS);
        while(!data.containsKey(tempDate)) {
          tempDate = now.minus(1, ChronoUnit.DAYS);
        }
        data.replace(tempDate, dataDaySet);
      }
    }
  }
}
