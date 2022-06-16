package de.ingenhaag.getmydeck.config.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
@ConfigurationPropertiesBinding
public class StringToOffsetDateTimeConverter implements Converter<String, OffsetDateTime> {

  @Override
  public OffsetDateTime convert(String source) {
    return OffsetDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(source)), ZoneOffset.UTC);
  }
}
