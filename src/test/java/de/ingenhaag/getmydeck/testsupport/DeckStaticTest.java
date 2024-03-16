package de.ingenhaag.getmydeck.testsupport;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
    "getmydeck.deckbot.scheduler.enabled=false",
    "getmydeck.persistence.enabled=true",
})
@ActiveProfiles("test")
public class DeckStaticTest extends AbstractIntegrationTest {
}
