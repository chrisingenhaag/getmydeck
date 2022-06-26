package de.ingenhaag.getmydeck.services;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ingenhaag.getmydeck.config.PersistenceConfiguration;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.persistence.DeckBotPersistenceObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;

@Service
public class DeckDataPersistenceService {

  private static final Logger log = LoggerFactory.getLogger(DeckDataPersistenceService.class);

  private DeckBotData currentDeckBotData;

  @Autowired
  PersistenceConfiguration persistenceConfiguration;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private Clock clock;

  private DeckBotPersistenceObject deckBotPersistenceObject;

  @PostConstruct
  public void init() {
    try {
      deckBotPersistenceObject = loadDataFromDisk();
      sanityCheckData();
    } catch (IOException e) {
      log.error("Error loading data from json file", e);
    }
  }

  private void sanityCheckData() {
    if(!deckBotPersistenceObject.isComplete()) {
      log.error("Loaded json data from filesystem is not complete, check data and restart: {}", deckBotPersistenceObject);
      System.exit(1);
    }
  }

  public void updateParsedDataIfChanged(SortedMap<Region, SortedMap<Version, OffsetDateTime>> parsedData) {
    DeckBotData deckBotData = new DeckBotData();
    deckBotData.setLastShipments(parsedData);
    deckBotData.setLastUpdated(OffsetDateTime.now(ZoneOffset.UTC));
    if (deckBotData.isComplete() &&
        (this.currentDeckBotData == null || !parsedData.entrySet().equals(this.currentDeckBotData.getLastShipments().entrySet()))) {
      this.currentDeckBotData = deckBotData;
      storeDataToDiskAndRefreshObject(deckBotData);
      log.info("Success updating deckBotData to {}", this.currentDeckBotData);
    } else {
      log.info("Data not changed or not complete on google sheet, skipping");
    }
  }

  public DeckBotData getDeckBotData() {
    if(currentDeckBotData == null) {
      currentDeckBotData = getLatestDataFromDisk();
    }
    return currentDeckBotData;
  }

  public Map<LocalDate, DeckBotData> getAllDataFromDisk() {
    return this.deckBotPersistenceObject.getAllDeckData();
  }

  private void storeDataToDiskAndRefreshObject(DeckBotData deckBotData) {
    try {
      File in = ResourceUtils.getFile(persistenceConfiguration.getPath());
      DeckBotPersistenceObject data;
      if(persistenceConfiguration.isCreateIfNotExists() && in.createNewFile()) {
        log.info("File {} not found, was created automatically", persistenceConfiguration.getPath());
        data = new DeckBotPersistenceObject();
      } else {
        // check for empty file
        data = mapper.readValue(in, DeckBotPersistenceObject.class);
      }

      data.addOrUpdateNewDeckDataSet(deckBotData, LocalDate.now(clock));

      File file = ResourceUtils.getFile(persistenceConfiguration.getPath());
      mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
      this.deckBotPersistenceObject = data;
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (StreamReadException e) {
      throw new RuntimeException(e);
    } catch (StreamWriteException e) {
      throw new RuntimeException(e);
    } catch (DatabindException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private DeckBotData getLatestDataFromDisk() {
    final Optional<DeckBotData> latestDeckData = this.deckBotPersistenceObject.getLatestDeckData();
    return latestDeckData.orElse(null);
  }

  private DeckBotPersistenceObject loadDataFromDisk() throws IOException {
    File file = ResourceUtils.getFile(persistenceConfiguration.getPath());
    return mapper.readValue(file, DeckBotPersistenceObject.class);
  }

}
