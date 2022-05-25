package de.ingenhaag.getmydeck.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
@ConfigurationPropertiesBinding
public class IntegerToOffsetDateTimeConverter implements Converter<Integer, OffsetDateTime> {

  @Override
  public OffsetDateTime convert(Integer source) {
    return OffsetDateTime.ofInstant(Instant.ofEpochSecond(source.longValue()), ZoneOffset.UTC);
  }
}
