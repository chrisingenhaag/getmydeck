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
import de.ingenhaag.getmydeck.testsupport.AbstractMongoContainerIntegrationTest;
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
import org.springframework.test.web.servlet.ResultActions;
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
class OpenApiControllerTest extends AbstractMongoContainerIntegrationTest implements JUnit5ValidationFileAssertions {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  void testGetOpenapiDocs() throws Exception {

    final MvcResult result = mvc.perform(MockMvcRequestBuilders
            .get("/api/openapi-docs")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn();

    final Object summary = objectMapper.readValue(result.getResponse().getContentAsString(), Object.class);

    assertWithFile(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(summary), FileExtensions.JSON);


  }

}