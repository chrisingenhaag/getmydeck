package de.ingenhaag.getmydeck.handler;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

  private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);


  @Override
  public void onEvent(CacheEvent<?, ?> event) {

    log.info("cache event {} {} {}",
        event.getKey(), event.getOldValue(), event.getNewValue());
  }
}
