package de.ingenhaag.getmydeck.services.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "getmydeck.persistence")
public class PersistenceConfiguration {

  private String path;
  private boolean createIfNotExists;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public boolean isCreateIfNotExists() {
    return createIfNotExists;
  }

  public void setCreateIfNotExists(boolean createIfNotExists) {
    this.createIfNotExists = createIfNotExists;
  }
}

