package de.ingenhaag.getmydeck.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void index() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .get("/")
            .accept(MediaType.TEXT_HTML))
        .andDo(print())
        .andExpect(content().contentType("text/html;charset=UTF-8"))
        .andExpect(status().isOk());
  }

  @Test
  void testStaticFavicon() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .get("/favicon.png"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.IMAGE_PNG))
        .andExpect(status().isOk());
  }
}