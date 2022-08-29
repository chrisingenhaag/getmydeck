package de.ingenhaag.getmydeck.services.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ingenhaag.getmydeck.services.support.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.services.support.deckbot.DeckBotPersistenceObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class DeckDataPersistenceService {

  private static final Logger log = LoggerFactory.getLogger(DeckDataPersistenceService.class);

  private DeckBotData currentDeckBotData;

  @Autowired
  PersistenceConfiguration persistenceConfiguration;

  @Autowired
  private ObjectMapper mapper;

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

  public DeckBotData getDeckBotData() {
    if(currentDeckBotData == null) {
      currentDeckBotData = getLatestDataFromDisk();
    }
    return currentDeckBotData;
  }

  public TreeMap<LocalDate, DeckBotData> getAllDataFromDisk() {
    return this.deckBotPersistenceObject.getAllDeckData();
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
