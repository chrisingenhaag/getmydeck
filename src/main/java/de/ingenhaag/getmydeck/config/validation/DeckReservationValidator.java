package de.ingenhaag.getmydeck.config.validation;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DeckReservationValidator implements ConstraintValidator<PlausibleDeckPreorderTime, OffsetDateTime> {

  @Autowired
  SteamConfiguration configuration;

  @Override
  public boolean isValid(OffsetDateTime value, ConstraintValidatorContext context) {
    return configuration.getReservationStart().isBefore(value)
        && value.isBefore(OffsetDateTime.now(ZoneOffset.UTC).plusDays(1));
  }
}
