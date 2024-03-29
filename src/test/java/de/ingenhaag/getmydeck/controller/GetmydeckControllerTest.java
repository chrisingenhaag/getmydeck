package de.ingenhaag.getmydeck.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cronn.assertions.validationfile.FileExtensions;
import de.cronn.assertions.validationfile.junit5.JUnit5ValidationFileAssertions;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.HistoricSummary;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import de.ingenhaag.getmydeck.services.DeckServiceTest;
import de.ingenhaag.getmydeck.services.SteamDeckMongoService;
import de.ingenhaag.getmydeck.testsupport.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import static de.ingenhaag.getmydeck.services.DeckServiceTest.RESERVED_TOO_EARLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class GetmydeckControllerTest extends AbstractIntegrationTest implements JUnit5ValidationFileAssertions {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @SpyBean
  SteamDeckMongoService database;

  @BeforeEach
  void each() {
    resetDataBase();
    LocalDate dayToRemove = LocalDate.of(2022, 6, 9);
    deleteDataSet(dayToRemove, Region.US, Version.S64);
  }

  @ParameterizedTest
  @MethodSource("getAllIterations")
  void testGetApiV2(Region region, Version version, String reservedAt) throws Exception {

    final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
            .get(String.format("/api/v2/regions/%s/versions/%s/infos/%s", region.toString(), version.getVersion(), reservedAt))
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    final InfoResponse infoResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), InfoResponse.class);
    infoResponse.getPersonalInfo().setLastDataUpdate(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1655141686), ZoneOffset.UTC));

    assertWithFileWithSuffix(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoResponse), String.join("_", region.toString(), version.getVersion(), reservedAt), FileExtensions.JSON);

    assertEquals(3, Mockito.mockingDetails(database).getInvocations().size());
  }

  private static Stream<Arguments> getAllIterations() {
    return Stream.of(
        Arguments.of(Region.EU, Version.S64, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.EU, Version.S256, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.EU, Version.S512, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.UK, Version.S64, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.UK, Version.S256, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.UK, Version.S512, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.US, Version.S64, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.US, Version.S256, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.US, Version.S512, DeckServiceTest.RESERVED_AT),
        Arguments.of(Region.EU, Version.S64, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.EU, Version.S256, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.EU, Version.S512, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.UK, Version.S64, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.UK, Version.S256, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.UK, Version.S512, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.US, Version.S64, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.US, Version.S256, DeckServiceTest.RESERVED_AT_PAST),
        Arguments.of(Region.US, Version.S512, DeckServiceTest.RESERVED_AT_PAST)
    );
  }

  @Test
  void testGetApiV2FailedRegion() throws Exception {
    final String reservedAt = DeckServiceTest.RESERVED_AT;

    mvc.perform(MockMvcRequestBuilders
            .get("/api/v2/regions/FOO/versions/512/infos/" + reservedAt)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());

  }

  @Test
  void testGetApiV2FailedVersion() throws Exception {
    final String reservedAt = DeckServiceTest.RESERVED_AT;

    mvc.perform( MockMvcRequestBuilders
            .get("/api/v2/regions/EU/versions/321/infos/"+reservedAt)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetApiV2TooEarlyReservation() throws Exception {

    mvc.perform( MockMvcRequestBuilders
            .get("/api/v2/regions/EU/versions/512/infos/"+RESERVED_TOO_EARLY)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetApiV2TooFarFutureReservation() throws Exception {
    OffsetDateTime future = OffsetDateTime.now(ZoneOffset.UTC).plusDays(2);

    mvc.perform( MockMvcRequestBuilders
            .get("/api/v2/regions/EU/versions/512/infos/"+future.toEpochSecond())
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetHistoricSummary() throws Exception {

    final MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders
            .get("/api/v2/summary")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    final HistoricSummary summary = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), HistoricSummary.class);

    assertWithFile(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(summary), FileExtensions.JSON);
    assertEquals(10, Mockito.mockingDetails(database).getInvocations().size());
  }

}