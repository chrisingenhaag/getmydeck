package de.ingenhaag.getmydeck.models.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class DeckBotPersistenceObject {

  private TreeMap<LocalDate, DeckBotDataDaySet> data;

  public TreeMap<LocalDate, DeckBotDataDaySet> getData() {
    return data;
  }

  public void setData(TreeMap<LocalDate, DeckBotDataDaySet> data) {
    this.data = data;
  }

  @JsonIgnore
  public Optional<DeckBotData> getLatestDeckData() {
    return data.entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getValue).map(DeckBotData::of);
  }

  @JsonIgnore
  public TreeMap<LocalDate, DeckBotData> getAllDeckData() {
    TreeMap<LocalDate, DeckBotData> result = new TreeMap<>();
    data.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(localDateDeckBotDataDaySetEntry -> Map.entry(localDateDeckBotDataDaySetEntry.getKey(), DeckBotData.of(localDateDeckBotDataDaySetEntry.getValue())))
        .forEach(localDateDeckBotDataEntry -> result.put(localDateDeckBotDataEntry.getKey(), localDateDeckBotDataEntry.getValue()));
    return result;
  }

  @JsonIgnore
  public void addOrUpdateNewDeckDataSet(DeckBotData deckBotData, LocalDate now) {
    DeckBotDataDaySet dataDaySet = DeckBotDataDaySet.of(deckBotData);

    if(data == null) {
      data = new TreeMap<>();
    }

    if(data.containsKey(now)) {
      data.replace(now, dataDaySet);
    } else {
      if(data.isEmpty() || now.getDayOfWeek().equals(DayOfWeek.MONDAY) || now.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
        data.put(now, dataDaySet);
      } else {
        // search existing day in dataset
        LocalDate tempDate = now.minus(1, ChronoUnit.DAYS);
        while(!data.containsKey(tempDate)) {
          tempDate = tempDate.minus(1, ChronoUnit.DAYS);
        }
        data.replace(tempDate, dataDaySet);
      }
    }
  }

  public boolean isComplete() {
    return data.entrySet().stream()
        .allMatch(localDateDeckBotDataDaySetEntry -> localDateDeckBotDataDaySetEntry.getValue().isComplete());
  }
}
