package de.ingenhaag.getmydeck.models.persistence.mongo;

import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SteamDeckQueueRepository extends MongoRepository<SteamDeckQueueDayEntry, String> {

  boolean existsByRegionAndVersionAndDayOfBatch(Region region, Version version, LocalDate dayOfBatch);

  SteamDeckQueueDayEntry findByRegionAndVersionAndDayOfBatch(Region region, Version version, LocalDate dayOfBatch);

  List<SteamDeckQueueDayEntry> findByRegionAndVersionOrderByDayOfBatchAsc(Region region, Version version);

  SteamDeckQueueDayEntry findFirstByRegionAndVersionOrderByDayOfBatchDesc(Region region, Version version);

  List<DayOfBatchOnly> findDistinctBy();

  void deleteByRegionAndVersionAndDayOfBatch(Region region, Version version, LocalDate dayOfBatch);

}
