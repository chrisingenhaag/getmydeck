package de.ingenhaag.getmydeck.config.validation;

import de.ingenhaag.getmydeck.config.SteamConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;

public class DeckReservationValidator implements ConstraintValidator<PlausibleDeckPreorderTime, OffsetDateTime> {

  @Autowired
  SteamConfiguration configuration;

  @Override
  public boolean isValid(OffsetDateTime value, ConstraintValidatorContext context) {
    return configuration.getReservationStart().isBefore(value);
  }
}
