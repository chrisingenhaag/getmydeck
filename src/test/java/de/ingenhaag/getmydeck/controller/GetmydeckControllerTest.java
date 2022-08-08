package de.ingenhaag.getmydeck.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cronn.assertions.validationfile.FileExtensions;
import de.cronn.assertions.validationfile.junit5.JUnit5ValidationFileAssertions;
import de.ingenhaag.getmydeck.models.deckbot.Region;
import de.ingenhaag.getmydeck.models.deckbot.Version;
import de.ingenhaag.getmydeck.models.dto.HistoricSummary;
import de.ingenhaag.getmydeck.models.dto.InfoResponse;
import de.ingenhaag.getmydeck.services.DeckServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static de.ingenhaag.getmydeck.services.DeckServiceTest.RESERVED_TOO_EARLY;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetmydeckControllerTest implements JUnit5ValidationFileAssertions {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @ParameterizedTest
  @MethodSource("getAllIterations")
  void testGetApiV2(Region region, Version version) throws Exception {
    final String reservedAt = DeckServiceTest.RESERVED_AT;

    final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
            .get(String.format("/api/v2/regions/%s/versions/%s/infos/%s", region.toString(), version.getVersion(), reservedAt))
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    final InfoResponse infoResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), InfoResponse.class);

    assertWithFileWithSuffix(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoResponse), region+version.getVersion(), FileExtensions.JSON);
  }

  private static Stream<Arguments> getAllIterations() {
    return Stream.of(
        Arguments.of(Region.EU, Version.S64),
        Arguments.of(Region.EU, Version.S256),
        Arguments.of(Region.EU, Version.S512),
        Arguments.of(Region.UK, Version.S64),
        Arguments.of(Region.UK, Version.S256),
        Arguments.of(Region.UK, Version.S512),
        Arguments.of(Region.US, Version.S64),
        Arguments.of(Region.US, Version.S256),
        Arguments.of(Region.US, Version.S512)
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
  void testGetHistoricSummary() throws Exception {

    final MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders
            .get("/api/v2/summary")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    final HistoricSummary summary = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), HistoricSummary.class);

    assertWithFile(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(summary));
  }

}