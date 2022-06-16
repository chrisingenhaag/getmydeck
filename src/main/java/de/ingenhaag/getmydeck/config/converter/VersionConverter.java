package de.ingenhaag.getmydeck.config.converter;

import de.ingenhaag.getmydeck.models.deckbot.Version;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VersionConverter implements Converter<String, Version> {
    @Override
    public Version convert(String value) {
      return Version.valueOfSize(value);
    }
}
