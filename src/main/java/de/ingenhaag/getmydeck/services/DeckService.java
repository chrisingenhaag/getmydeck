package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.*;
import de.ingenhaag.getmydeck.models.persistence.mongo.SteamDeckQueueDayEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class DeckService {

  @Autowired
  private SteamDeckMongoService steamDeckMongoService;

  @Autowired
  private SteamConfiguration config;

  public InfoResponse getPersonalInfos(OffsetDateTime reservedAt, Region region, Version version) {
    SteamDeckQueueDayEntry latestOrderSpecificVersion = steamDeckMongoService.getLatestData(region, version);

    OfficialInfo officialInfo = new OfficialInfo();
    officialInfo.setReservationsStartedAt(config.getReservationStart());

    PersonalInfo personalInfo = new PersonalInfo();
    personalInfo.setRegion(region);
    personalInfo.setVersion(version);
    personalInfo.setLastDataUpdate(latestOrderSpecificVersion.getLastModified().atOffset(ZoneOffset.UTC));
    personalInfo.setLastBatchDate(latestOrderSpecificVersion.getDayOfBatch());
    personalInfo.setLatestOrderSeconds(latestOrderSpecificVersion.getLatestOrder());
    personalInfo.setLatestOrder(getOffsetDateTime(latestOrderSpecificVersion));

    personalInfo.setReservedAt(reservedAt);
    personalInfo.setDurationReservedAfterStart(getDurationBetweenStartAndPersonalReservation(reservedAt));
    personalInfo.setDurationReservedAfterStartHumanReadable(humanReadableDuration(getDurationBetweenStartAndPersonalReservation(reservedAt)));
    final Double elapsedTimePercentage = calculateElapsedTimePercentage(reservedAt, getOffsetDateTime(latestOrderSpecificVersion));
    personalInfo.setElapsedTimePercentage(elapsedTimePercentage);

    final List<HistoricDeckbotData> historicData = getHistoricData(reservedAt, region, version);
    personalInfo.setHistoricData(historicData);

    personalInfo.setPrettyText(calcPrettyText(personalInfo, region, version, getOffsetDateTime(latestOrderSpecificVersion), reservedAt));
    personalInfo.setHtmlText(calcHtmlText(personalInfo, region, version, getOffsetDateTime(latestOrderSpecificVersion), reservedAt));

    InfoResponse info = new InfoResponse();
    info.setOfficialInfo(officialInfo);
    info.setPersonalInfo(personalInfo);
    return info;
  }

  private OffsetDateTime getOffsetDateTime(SteamDeckQueueDayEntry latestOrderSpecificVersion) {
    return getOffsetDateTime(latestOrderSpecificVersion.getLatestOrder());
  }

  private OffsetDateTime getOffsetDateTime(long epochSecond) {
    return OffsetDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneOffset.UTC);
  }

  public HistoricSummary getHistoricSummary() {
    HistoricSummary summary = new HistoricSummary();
    SortedMap<Region, SortedMap<Version, HistoricSummarySet>> regionMap = new TreeMap<>();
    Arrays.stream(Region.values()).forEach(region -> {
      SortedMap<Version, HistoricSummarySet> versionMap = new TreeMap<>();
      Arrays.stream(Version.values()).forEach(version -> {
       versionMap.put(version, calcHistSummarySetFor(region, version));
      });
      regionMap.put(region, versionMap);
    });
    summary.setSummary(regionMap);
    return summary;
  }

  private HistoricSummarySet calcHistSummarySetFor(Region region, Version version) {
    SortedMap<LocalDate, HistoricSummaryEntry> entries = new TreeMap<>();

    for (SteamDeckQueueDayEntry data : steamDeckMongoService.getAllDataFromQueue(region, version)) {
      LocalDate date = data.getDayOfBatch();

      HistoricSummaryEntry entry = new HistoricSummaryEntry();
      entry.setLastOrderSeconds(data.getLatestOrder());
      // not possible as lastmodified by spring-data mongo db can´t be time mocked
      // entry.setLastUpdate(getOffsetDateTime(data.getLastModified().toEpochSecond(ZoneOffset.UTC)));
      // TODO
      //entry.setElapsedSeconds();

      entries.put(date, entry);
    }



    HistoricSummarySet summarySet = new HistoricSummarySet();
    summarySet.setEntries(entries);
    return summarySet;
  }

  private String calcHtmlText(PersonalInfo personalInfo, Region region, Version version, OffsetDateTime latestOrderSpecificVersion, OffsetDateTime reservedAt) {
    final Optional<HistoricDeckbotData> latestHistoricEntry = personalInfo.getHistoricData()
        .stream()
        .filter(historicDeckbotData -> historicDeckbotData.getDate().equals(personalInfo.getLastBatchDate()))
        .findFirst();

    Duration latestIncrease = null;
    if (latestHistoricEntry.isPresent() && latestHistoricEntry.get().getIncreasedSeconds() != null) {
      latestIncrease = Duration.ofSeconds(latestHistoricEntry.get().getIncreasedSeconds());
    }

    if(personalInfo.getElapsedTimePercentage() < 100.) {
      return String.format("""
              <ul>
                <li>You placed a %s %sGB reservation on %s (UTC)</li>
                <li>That is %s after pre-orders opened</li>
                <li>%s worth of pre-orders have been processed</li>
                <li>That`s %s more than last batch</li>
                <li>You have %s worth of pre-orders to go until it is your turn (not real time)</li>
                <li>You're %s %% of the way there!</li>
              </ul>""",
          region,
          version.getVersion(),
          personalInfo.getReservedAt(),
          personalInfo.getDurationReservedAfterStartHumanReadable(),
          calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
          latestIncrease != null ? humanReadableDuration(latestIncrease) : '-',
          calculateDurationBetweenLastShipmentAndMyReservation(reservedAt, latestOrderSpecificVersion),
          personalInfo.getElapsedTimePercentage());
    }
    return String.format("""
              <ul>
                <li>You placed a %s %sGB reservation on %s (UTC)</li>
                <li>That is %s after pre-orders opened</li>
                <li>%s worth of pre-orders have been processed</li>
                <li>That`s %s more than last batch</li>
                <li>You're %s %% of the way there</li>
                <li>Apparently someone was already able to order with a later reservation time than yours</li>
                <li>You should have received your mail from valve!</li>
              </ul>""",
        region,
        version.getVersion(),
        personalInfo.getReservedAt(),
        personalInfo.getDurationReservedAfterStartHumanReadable(),
        calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
        latestIncrease != null ? humanReadableDuration(latestIncrease) : '-',
        personalInfo.getElapsedTimePercentage());
  }

  private String calcPrettyText(PersonalInfo personalInfo, Region region, Version version, OffsetDateTime latestOrderSpecificVersion, OffsetDateTime reservedAt) {
    final Optional<HistoricDeckbotData> latestHistoricEntry = personalInfo.getHistoricData()
        .stream()
        .filter(historicDeckbotData -> historicDeckbotData.getDate().equals(personalInfo.getLastBatchDate()))
        .findFirst();

    Duration latestIncrease = null;
    if (latestHistoricEntry.isPresent() && latestHistoricEntry.get().getIncreasedSeconds() != null) {
      latestIncrease = Duration.ofSeconds(latestHistoricEntry.get().getIncreasedSeconds());
    }

    if(personalInfo.getElapsedTimePercentage() < 100.) {
      return String.format("""
              You placed a %s %sGB reservation on %s (UTC). That is %s after pre-orders opened. 
              %s worth of pre-orders have been processed (not real time). 
              That`s %s more than last batch and you have %s of pre-orders to go until it is your turn. 
              You're %s %% of the way there!""",
          region,
          version.getVersion(),
          personalInfo.getReservedAt(),
          personalInfo.getDurationReservedAfterStartHumanReadable(),
          calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
          calculateDurationBetweenLastShipmentAndMyReservation(reservedAt, latestOrderSpecificVersion),
          latestIncrease != null ? humanReadableDuration(latestIncrease) : '-',
          personalInfo.getElapsedTimePercentage());
    }
    return String.format("""
              You placed a %s %sGB reservation on %s (UTC). You reserved your deck %s after pre-orders opened. 
              %s worth of pre-orders have been processed (not real time). 
              That`s %s more than last batch.  
              This is %s %% so you should have received your order information from valve. 
              Check your mail and spam folder!""",
        region,
        version.getVersion(),
        personalInfo.getReservedAt(),
        personalInfo.getDurationReservedAfterStartHumanReadable(),
        calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
        latestIncrease != null ? humanReadableDuration(latestIncrease) : '-',
        personalInfo.getElapsedTimePercentage());
  }

  private List<HistoricDeckbotData> getHistoricData(OffsetDateTime reservedAt, Region region, Version version) {
    List<HistoricDeckbotData> result = new ArrayList<>();

    final List<SteamDeckQueueDayEntry> allDataFromQueue = steamDeckMongoService.getAllDataFromQueue(region, version);

    final List<LocalDate> allDayOfBatches = steamDeckMongoService.getAllDayOfBatches();

    double lastPercentage = 0.;
    long lastSeconds = 0l;
    for(SteamDeckQueueDayEntry entry : allDataFromQueue) {
      HistoricDeckbotData historicDeckbotData = new HistoricDeckbotData();
      historicDeckbotData.setDate(entry.getDayOfBatch());

      allDayOfBatches.remove(entry.getDayOfBatch());

      final Double elapsedTimePercentage = calculateElapsedTimePercentage(reservedAt, getOffsetDateTime(entry));
      historicDeckbotData.setElapsedTimePercentage(elapsedTimePercentage);

      final Long elapsedSeconds =  calculateElapsedTimeSeconds(getOffsetDateTime(entry));
      historicDeckbotData.setElapsedSeconds(elapsedSeconds);

      historicDeckbotData.setIncreasedSeconds(elapsedSeconds - lastSeconds);
      lastSeconds = elapsedSeconds;

      historicDeckbotData.setIncreasedPercentage((double) ((int) ((elapsedTimePercentage - lastPercentage) * 100)) / (100));
      lastPercentage = elapsedTimePercentage;

      result.add(historicDeckbotData);
    }

    allDayOfBatches.forEach(localDate -> {
      HistoricDeckbotData emptyHistoricData = new HistoricDeckbotData();
      emptyHistoricData.setDate(localDate);
      result.add(emptyHistoricData);
    });

    result.sort(Comparator.comparing(HistoricDeckbotData::getDate).reversed());
    return new ArrayList<>(result);
  }

  private Duration getDurationBetweenStartAndPersonalReservation(OffsetDateTime reservedAt) {
    return Duration.between(config.getReservationStart(), reservedAt);
  }

  private String calculateDurationBetweenPreorderStartAndLastShipment(OffsetDateTime latestOrderSpecificVersion) {
    Duration d = Duration.between(config.getReservationStart(), latestOrderSpecificVersion);
    return humanReadableDuration(d);
  }

  private String calculateDurationBetweenLastShipmentAndMyReservation(OffsetDateTime reservedAt, OffsetDateTime latestOrderSpecificVersion) {
    Duration d = Duration.between(latestOrderSpecificVersion, reservedAt);
    return humanReadableDuration(d);
  }
  private Double calculateElapsedTimePercentage(OffsetDateTime reservedAt, OffsetDateTime latestOrderSpecificVersion) {
    Duration diffToMyPersonalReservation = getDurationBetweenStartAndPersonalReservation(reservedAt);

    Duration diffToLastShipment = Duration.between(config.getReservationStart(), latestOrderSpecificVersion);

    double percentage = (double) diffToLastShipment.getSeconds() * 100 / diffToMyPersonalReservation.getSeconds();
    return (double) ((int) (percentage * 100)) / (100);
  }

  private Long calculateElapsedTimeSeconds(OffsetDateTime latestOrderSpecificVersion) {
    return Duration.between(config.getReservationStart(), latestOrderSpecificVersion).getSeconds();
  }

  private String humanReadableDuration(Duration duration) {
    long d = duration.toDays();
    long HH = duration.toHoursPart();
    long MM = duration.toMinutesPart();
    long SS = duration.toSecondsPart();
    return String.format("%02d days, %02d hours, %02d minutes and %02d seconds", d, HH, MM, SS);
  }
}
