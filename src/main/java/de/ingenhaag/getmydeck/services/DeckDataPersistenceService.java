package de.ingenhaag.getmydeck.services;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ingenhaag.getmydeck.models.Region;
import de.ingenhaag.getmydeck.models.Version;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.models.persistence.DeckBotPersistenceObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;

@Service
public class DeckDataPersistenceService {

  private static final Logger log = LoggerFactory.getLogger(DeckDataPersistenceService.class);

  private DeckBotData currentDeckBotData;

  @Value("${persistence.path}")
  private String path;

  @Value("${persistence.create-if-not-exists}")
  private boolean createIfNotExists;

  @Autowired
  private ObjectMapper mapper;

  private DeckBotPersistenceObject deckBotPersistenceObject;

  @PostConstruct
  public void init() {
    try {
      deckBotPersistenceObject = loadDataFromDisk();
      this.currentDeckBotData = getLatestDataFromDisk();
    } catch (IOException e) {
      log.error("Error loading data from json file", e);
    }
  }

  public void updateParsedDataIfChanged(Map<Region, Map<Version, OffsetDateTime>> parsedData) {
    DeckBotData deckBotData = new DeckBotData();
    deckBotData.setLastShipments(parsedData);
    deckBotData.setLastUpdated(OffsetDateTime.now(ZoneOffset.UTC));
    if (deckBotData.isComplete() &&
        (this.currentDeckBotData == null || !parsedData.entrySet().equals(this.currentDeckBotData.getLastShipments().entrySet()))) {
      this.currentDeckBotData = deckBotData;
      storeDataToDisk(deckBotData);
      log.info("Success updating deckBotData to {}", this.currentDeckBotData);
    } else {
      log.info("Data not changed on google sheet, skipping");
    }
  }

  public DeckBotData getDeckBotData() {
    return currentDeckBotData;
  }

  public void storeDataToDisk(DeckBotData deckBotData) {
    try {
      File in = new File(path);
      DeckBotPersistenceObject data;
      if(createIfNotExists && in.createNewFile()) {
        log.info("File {} not found, was created automatically", path);
        data = new DeckBotPersistenceObject();
      } else {
        data = mapper.readValue(in, DeckBotPersistenceObject.class);
      }

      data.addOrUpdateNewDeckDataSet(deckBotData);

      File file = new File(path);
      mapper.writeValue(file, data);

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

  public DeckBotData getLatestDataFromDisk() {
    final Optional<DeckBotData> latestDeckData = this.deckBotPersistenceObject.getLatestDeckData();
    return latestDeckData.orElse(null);
  }

  public DeckBotPersistenceObject loadDataFromDisk() throws IOException {
    File file = new File(path);
    return mapper.readValue(file, DeckBotPersistenceObject.class);
  }

}
