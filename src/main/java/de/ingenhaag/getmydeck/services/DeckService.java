package de.ingenhaag.getmydeck.services;

import com.sun.source.tree.Tree;
import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class DeckService {

  @Autowired
  private DeckDataPersistenceService deckDataPersistenceService;
  @Autowired
  private SteamConfiguration config;

  private final OfficialInfo officialInfo = new OfficialInfo();

  public InfoResponse getPersonalInfos(OffsetDateTime reservedAt, Region region, Version version) {
    this.officialInfo.setReservationsStartedAt(config.getReservationStart());

    OffsetDateTime latestOrderSpecificVersion = getSelectedDeckLastShipment(region, version);

    PersonalInfo personalInfo = new PersonalInfo();
    personalInfo.setRegion(region);
    personalInfo.setVersion(version);
    personalInfo.setLatestOrderSeconds(latestOrderSpecificVersion.toEpochSecond());
    personalInfo.setLatestOrder(latestOrderSpecificVersion);

    personalInfo.setReservedAt(reservedAt);
    personalInfo.setDurationReservedAfterStart(getDurationBetweenStartAndPersonalReservation(reservedAt));
    personalInfo.setDurationReservedAfterStartHumanReadable(humanReadableDuration(getDurationBetweenStartAndPersonalReservation(reservedAt)));
    final Double elapsedTimePercentage = calculateElapsedTimePercentage(reservedAt, latestOrderSpecificVersion);
    personalInfo.setElapsedTimePercentage(elapsedTimePercentage);
    personalInfo.setPrettyText(calcPrettyText(personalInfo, region, version, latestOrderSpecificVersion, reservedAt));
    personalInfo.setHtmlText(calcHtmlText(personalInfo, region, version, latestOrderSpecificVersion, reservedAt));
    final List<HistoricDeckbotData> historicData = getHistoricData(reservedAt, region, version);
    personalInfo.setHistoricData(historicData);

    InfoResponse info = new InfoResponse();
    info.setOfficialInfo(officialInfo);
    info.setPersonalInfo(personalInfo);
    return info;
  }

  public HistoricSummary getHistoricSummary() {
    HistoricSummary summary = new HistoricSummary();
    summary.setLastUpdated(deckDataPersistenceService.getDeckBotData().getLastUpdated());
    SortedMap<Region, SortedMap<Version, HistoricSummarySet>> regionMap = new TreeMap<>();
    final TreeMap<LocalDate, DeckBotData> allDataFromDisk = deckDataPersistenceService.getAllDataFromDisk();
    Arrays.stream(Region.values()).forEach(region -> {
      SortedMap<Version, HistoricSummarySet> versionMap = new TreeMap<>();
      Arrays.stream(Version.values()).forEach(version -> {
       versionMap.put(version, calcHistSummarySetFor(region, version, allDataFromDisk));
      });
      regionMap.put(region, versionMap);
    });
    summary.setSummary(regionMap);
    return summary;
  }

  private HistoricSummarySet calcHistSummarySetFor(Region region, Version version, TreeMap<LocalDate, DeckBotData> allDataFromDisk) {
    HistoricSummarySet summarySet = new HistoricSummarySet();

    List<String> dateList = new ArrayList<>();
    List<Long> increaseList = new ArrayList<>();

    long rememberLastTimeStamp = 0L;
    for (Map.Entry<LocalDate, DeckBotData> entry : allDataFromDisk.entrySet()) {
      LocalDate date = entry.getKey();
      DeckBotData deckBotData = entry.getValue();
      final String prettyDate = String.format("%s-%s", date.getMonthValue(), date.getDayOfMonth());
      dateList.add(prettyDate);
      final OffsetDateTime dateTime = deckBotData.getLastShipments().get(region).get(version);
      if (increaseList.size() == 0) {
        increaseList.add(0L);
      } else {
        increaseList.add(
            dateTime.toEpochSecond() - rememberLastTimeStamp);
      }
      rememberLastTimeStamp = dateTime.toEpochSecond();
    }

    summarySet.setIncreaseDateList(dateList);
    summarySet.setIncreaseTimeList(increaseList);
    return summarySet;
  }

  private String calcHtmlText(PersonalInfo personalInfo, Region region, Version version, OffsetDateTime latestOrderSpecificVersion, OffsetDateTime reservedAt) {
    if(personalInfo.getElapsedTimePercentage() < 100.) {
      return String.format("""
              <ul>
                <li>It looks like you have a %s %sGB reservation</li>
                <li>You reserved your deck %s after pre-orders opened</li>
                <li>%s worth of pre-orders have been processed</li>
                <li>You have %s worth of pre-orders to go until it is your turn (not real time)</li>
                <li>You're %s %% of the way there!</li>
              </ul>""",
          region,
          version.getVersion(),
          personalInfo.getDurationReservedAfterStartHumanReadable(),
          calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
          calculateDurationBetweenLastShipmentAndMyReservation(reservedAt, latestOrderSpecificVersion),
          personalInfo.getElapsedTimePercentage());
    }
    return String.format("""
              <ul>
                <li>It looks like you have a %s %sGB reservation</li>
                <li>You reserved your deck %s after pre-orders opened</li>
                <li>%s worth of pre-orders have been processed</li>
                <li>You're %s %% of the way there</li>
                <li>Apparently someone was already able to order with a later reservation time than yours</li>
                <li>You should have received your mail from valve!</li>
              </ul>""",
        region,
        version.getVersion(),
        personalInfo.getDurationReservedAfterStartHumanReadable(),
        calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
        personalInfo.getElapsedTimePercentage());
  }

  private String calcPrettyText(PersonalInfo personalInfo, Region region, Version version, OffsetDateTime latestOrderSpecificVersion, OffsetDateTime reservedAt) {
    if(personalInfo.getElapsedTimePercentage() < 100.) {
      return String.format("""
              It looks like you have a %s %sGB reservation. 
              You reserved your deck %s after pre-orders opened. 
              %s worth of pre-orders have been processed (not real time), and you have %s of pre-orders to go until it is your turn. 
              You're %s %% of the way there!""",
          region,
          version.getVersion(),
          personalInfo.getDurationReservedAfterStartHumanReadable(),
          calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
          calculateDurationBetweenLastShipmentAndMyReservation(reservedAt, latestOrderSpecificVersion),
          personalInfo.getElapsedTimePercentage());
    }
    return String.format("""
              It looks like you have a %s %sGB reservation. 
              You reserved your deck %s after pre-orders opened. 
              %s worth of pre-orders have been processed (not real time). This is %s %% so you should have received your order information from valve. 
              Check your mail and spam folder!""",
        region,
        version.getVersion(),
        personalInfo.getDurationReservedAfterStartHumanReadable(),
        calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
        personalInfo.getElapsedTimePercentage());
  }

  private List<HistoricDeckbotData> getHistoricData(OffsetDateTime reservedAt, Region region, Version version) {
    List<HistoricDeckbotData> result = new ArrayList<>();

    final Map<LocalDate, DeckBotData> allDataFromDisk = deckDataPersistenceService.getAllDataFromDisk();
    final Set<LocalDate> dates = allDataFromDisk.keySet();
    final Iterator<LocalDate> it = dates.iterator();
    while(it.hasNext()) {
      final LocalDate next = it.next();
      final DeckBotData deckBotData = allDataFromDisk.get(next);

      HistoricDeckbotData historicDeckbotData = new HistoricDeckbotData();
      historicDeckbotData.setDate(next);
      historicDeckbotData.setElapsedTimePercentage(calculateElapsedTimePercentage(reservedAt, deckBotData.getLastShipments().get(region).get(version)));

      result.add(historicDeckbotData);
    }
    result.sort(Comparator.comparing(HistoricDeckbotData::getDate).reversed());
    return new ArrayList<>(result);
  }

  private SortedMap<Region, SortedMap<Version, OffsetDateTime>> getDeckBotDataOrDefault() {
    final DeckBotData deckBotData = deckDataPersistenceService.getDeckBotData();
    if (Objects.nonNull(deckBotData) && deckBotData.isComplete()) {
      this.officialInfo.setLastDataUpdate(deckBotData.getLastUpdated());
      this.officialInfo.setLastDataUpdateDate(deckBotData.getLastUpdated().toLocalDate());

      final SortedMap<Region, SortedMap<Version, OffsetDateTime>> lastShipments = deckBotData.getLastShipments();


      this.officialInfo.setLastShipments(lastShipments);
      return lastShipments;
    }
    return null;
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

  private String humanReadableDuration(Duration duration) {
    long d = duration.toDays();
    long HH = duration.toHoursPart();
    long MM = duration.toMinutesPart();
    long SS = duration.toSecondsPart();
    return String.format("%02d days, %02d hours, %02d minutes and %02d seconds", d, HH, MM, SS);
  }

  private OffsetDateTime getSelectedDeckLastShipment(Region region, Version version) {
    return getDeckBotDataOrDefault().get(region).get(version);
  }
}
