package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueDayEntry;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.SortedMap;

@Component
public class SteamDeckMongoService {

  private static final Logger log = LoggerFactory.getLogger(SteamDeckMongoService.class);


  @Autowired
  private DeckDataPersistenceService deckDataPersistenceService;

  @Autowired
  private Clock clock;

  @Autowired
  SteamDeckQueueRepository repo;

  public SteamDeckQueueDayEntry getLatestData(Region region, Version version) {
    return repo.findFirstByRegionAndVersionOrderByDayOfBatchDesc(region, version);
  }

  public List<SteamDeckQueueDayEntry> getAllDataFromQueue(Region region, Version version) {
    return repo.findByRegionAndVersionOrderByDayOfBatchAsc(region, version);
  };

  public void updateParsedDataIfChanged(SortedMap<Region, SortedMap<Version, OffsetDateTime>> parsedData) {
    final LocalDate now = LocalDate.now(clock);

    parsedData.forEach((region, versionOffsetDateTimeSortedMap) -> {
      versionOffsetDateTimeSortedMap.forEach((version, offsetDateTime) -> {
        if(!repo.existsByRegionAndVersionAndDayOfBatch(region,version,now)) {
          if(now.getDayOfWeek().equals(DayOfWeek.MONDAY) || now.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
            SteamDeckQueueDayEntry day = new SteamDeckQueueDayEntry();
            day.setDayOfBatch(now);
            day.setRegion(region);
            day.setVersion(version);
            day.setLatestOrder(offsetDateTime.toEpochSecond());
            repo.save(day);
          } else {
            // TODO maybe add check for not jumping over a missing day, this here assumes last monday exists
            final SteamDeckQueueDayEntry dayOfBatch = repo.findFirstByRegionAndVersionOrderByDayOfBatchDesc(region, version);
            updateIfNewer(offsetDateTime, dayOfBatch);
          }
        } else {
          final SteamDeckQueueDayEntry entry = repo.findByRegionAndVersionAndDayOfBatch(region, version, now);
          updateIfNewer(offsetDateTime, entry);
        }
      });
    });
  }

  private void updateIfNewer(OffsetDateTime offsetDateTime, SteamDeckQueueDayEntry entry) {
    if(!entry.getLatestOrder().equals(offsetDateTime.toEpochSecond())) {
      entry.setLatestOrder(offsetDateTime.toEpochSecond());
      repo.save(entry);
    } else {
      log.info("object not newer, ignoring");
    }
  }

  public void migrateDataToMongo() {
    deckDataPersistenceService.getAllDataFromDisk().forEach((localDate, deckBotData) -> {
      final OffsetDateTime lastUpdated = deckBotData.getLastUpdated();
      deckBotData.getLastShipments().forEach((region, versionOffsetDateTimeSortedMap) -> {
        versionOffsetDateTimeSortedMap.forEach((version, offsetDateTime) -> {
          SteamDeckQueueDayEntry entry = new SteamDeckQueueDayEntry();
          entry.setLastModified(lastUpdated.toLocalDateTime());
          entry.setRegion(region);
          entry.setVersion(version);
          entry.setDayOfBatch(localDate);
          entry.setLatestOrder(offsetDateTime.toEpochSecond());
          repo.save(entry);
        });
      });
    });
  }
}