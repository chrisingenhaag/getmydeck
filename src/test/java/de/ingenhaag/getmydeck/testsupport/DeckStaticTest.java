package de.ingenhaag.getmydeck.testsupport;

import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
    "getmydeck.deckbot.scheduler.enabled=false"
})
public class DeckStaticTest {
}
