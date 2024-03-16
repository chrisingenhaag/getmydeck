package de.ingenhaag.getmydeck.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cronn.assertions.validationfile.FileExtensions;
import de.cronn.assertions.validationfile.junit5.JUnit5ValidationFileAssertions;
import de.ingenhaag.getmydeck.testsupport.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
class OpenApiControllerTest extends AbstractIntegrationTest implements JUnit5ValidationFileAssertions {

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