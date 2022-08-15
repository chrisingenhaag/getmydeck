package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.persistence.mongo.DayOfBatchOnly;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueDayEntry;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SteamDeckMongoService {

  private static final Logger log = LoggerFactory.getLogger(SteamDeckMongoService.class);


  @Autowired
  private DeckDataPersistenceService deckDataPersistenceService;

  @Autowired
  private Clock clock;

  @Autowired
  SteamDeckQueueRepository repo;

  @Autowired
  CacheManager cacheManager;

  public SteamDeckQueueDayEntry getLatestData(Region region, Version version) {
    return repo.findFirstByRegionAndVersionOrderByDayOfBatchDesc(region, version);
  }

  public List<SteamDeckQueueDayEntry> getAllDataFromQueue(Region region, Version version) {
    return repo.findByRegionAndVersionOrderByDayOfBatchAsc(region, version);
  };

  @Cacheable(value = "allBatchDays")
  public List<LocalDate> getAllDayOfBatches() {
    final List<DayOfBatchOnly> list = repo.findDistinctBy();

    return list.stream()
        .flatMap(dayOfBatchOnly -> Stream.of(dayOfBatchOnly.getDayOfBatch()))
        .distinct()
        .collect(Collectors.toList());
  }

  public void updateParsedDataIfChanged(SortedMap<Region, SortedMap<Version, OffsetDateTime>> parsedData) {
    final LocalDate now = LocalDate.now(clock);

    parsedData.forEach((region, versionOffsetDateTimeSortedMap) -> {
      versionOffsetDateTimeSortedMap.forEach((version, offsetDateTime) -> {
        if(!repo.existsByRegionAndVersionAndDayOfBatch(region,version,now)) {
          if(now.getDayOfWeek().equals(DayOfWeek.MONDAY) || now.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
            SteamDeckQueueDayEntry newDay = new SteamDeckQueueDayEntry();
            newDay.setDayOfBatch(now);
            newDay.setRegion(region);
            newDay.setVersion(version);
            newDay.setLatestOrder(offsetDateTime.toEpochSecond());

            final SteamDeckQueueDayEntry lastKnownBatch = repo.findFirstByRegionAndVersionOrderByDayOfBatchDesc(region, version);
            if(!lastKnownBatch.getLatestOrder().equals(offsetDateTime.toEpochSecond())) {
              triggerAllBatchDayInvalidation();
              repo.save(newDay);
            }
          } else {
            LocalDate lastMondayOrThursday = calcLastMondayOrThursday();
            if (repo.existsByRegionAndVersionAndDayOfBatch(region, version, lastMondayOrThursday)) {
              final SteamDeckQueueDayEntry dayOfBatch = repo.findFirstByRegionAndVersionOrderByDayOfBatchDesc(region, version);
              updateIfOffsetDiffers(offsetDateTime, dayOfBatch);
            } else {
              SteamDeckQueueDayEntry newPastDay = new SteamDeckQueueDayEntry();
              newPastDay.setDayOfBatch(now);
              newPastDay.setRegion(region);
              newPastDay.setVersion(version);
              newPastDay.setLatestOrder(offsetDateTime.toEpochSecond());
              log.info("Saving new past day {}", newPastDay);
              triggerAllBatchDayInvalidation();
              repo.save(newPastDay);
            }
          }
        } else {
          final SteamDeckQueueDayEntry entry = repo.findByRegionAndVersionAndDayOfBatch(region, version, now);
          updateIfOffsetDiffers(offsetDateTime, entry);
        }
      });
    });
  }

  private LocalDate calcLastMondayOrThursday() {
    final LocalDate lastMonday = LocalDate.now(clock).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    final LocalDate lastThursday = LocalDate.now(clock).with(TemporalAdjusters.previous(DayOfWeek.THURSDAY));

    return lastThursday.isAfter(lastMonday) ? lastThursday : lastMonday;
  }

  private void updateIfOffsetDiffers(OffsetDateTime offsetDateTime, SteamDeckQueueDayEntry entry) {
    if(!entry.getLatestOrder().equals(offsetDateTime.toEpochSecond())) {
      entry.setLatestOrder(offsetDateTime.toEpochSecond());
      final SteamDeckQueueDayEntry savedEntity = repo.save(entry);
      log.info("offset differs, update it in database {}", savedEntity);
    } else {
      log.debug("object not differing, ignoring");
    }
  }

  public void deleteDataSet(LocalDate day, Region region, Version version) {
    repo.deleteByRegionAndVersionAndDayOfBatch(region,version,day);
    triggerAllBatchDayInvalidation();
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
    triggerAllBatchDayInvalidation();
  }

  private void triggerAllBatchDayInvalidation() {
    final Cache allBatchDays = cacheManager.getCache("allBatchDays");
    if(allBatchDays != null) {
      allBatchDays.invalidate();
    }
    log.info("Trigger cache invalidation for allBatchDays");
  }
}
