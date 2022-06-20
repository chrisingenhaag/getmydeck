package de.ingenhaag.getmydeck.services;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import de.ingenhaag.getmydeck.models.deckbot.DeckBotData;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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
    personalInfo.setPredictiveData(calcPredictiveDataFromHistory(elapsedTimePercentage, historicData));

    InfoResponse info = new InfoResponse();
    info.setOfficialInfo(officialInfo);
    info.setPersonalInfo(personalInfo);
    return info;
  }

  private String calcHtmlText(PersonalInfo personalInfo, Region region, Version version, OffsetDateTime latestOrderSpecificVersion, OffsetDateTime reservedAt) {
    if(personalInfo.getElapsedTimePercentage() < 100.) {
      return String.format("""
              <ul>
                <li>It looks like you have a %s %sGB reservation</li>
                <li>You reserved your deck %s after pre-orders opened</li>
                <li>%s of orders have been processed</li>
                <li>You have %s of orders to go until it is your turn</li>
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
                <li>%s of orders have been processed</li>
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
              %s of orders have been processed, and you have %s of orders to go until it is your turn. 
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
              %s of orders have been processed. This is %s %% so you should have received your order information from valve. 
              Check your mail and spam folder!""",
        region,
        version.getVersion(),
        personalInfo.getDurationReservedAfterStartHumanReadable(),
        calculateDurationBetweenPreorderStartAndLastShipment(latestOrderSpecificVersion),
        personalInfo.getElapsedTimePercentage());
  }

  private PredictiveData calcPredictiveDataFromHistory(Double currentElapsedTimePercentage, List<HistoricDeckbotData> historicData) {
    PredictiveData predictiveData = new PredictiveData();

    final Double oldPercentage = historicData.get(4).getElapsedTimePercentage();
    final LocalDate date = historicData.get(4).getDate();
    long daysBetween = date.until(LocalDate.now(ZoneOffset.UTC), ChronoUnit.DAYS);
    Double averageIncreasePerDay = (currentElapsedTimePercentage - oldPercentage) / daysBetween;
    int daysToGo = 100 - Double.valueOf(currentElapsedTimePercentage / averageIncreasePerDay).intValue() + 1;

    predictiveData.setFiveShipmentAverage(LocalDate.ofInstant(Instant.now().plus(daysToGo, ChronoUnit.DAYS), ZoneOffset.UTC));

    return predictiveData;
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

  private Map<Region, Map<Version, OffsetDateTime>> getDeckBotDataOrDefault() {
    final DeckBotData deckBotData = deckDataPersistenceService.getDeckBotData();
    if (Objects.nonNull(deckBotData) && deckBotData.isComplete()) {
      this.officialInfo.setLastDataUpdate(deckBotData.getLastUpdated());
      this.officialInfo.setLastDataUpdateDate(deckBotData.getLastUpdated().toLocalDate());
      this.officialInfo.setLastShipments(deckBotData.getLastShipments());
      return deckBotData.getLastShipments();
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

  public OffsetDateTime getSelectedDeckLastShipment(Region region, Version version) {
    return getDeckBotDataOrDefault().get(region).get(version);
  }

  public Map<Version, OffsetDateTime> getSelectedDeckRegion(Region region) {
    return getDeckBotDataOrDefault().get(region);
  }
}
