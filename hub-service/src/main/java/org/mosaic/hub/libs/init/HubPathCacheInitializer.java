package org.mosaic.hub.libs.init;

import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.service.HubShortPathService;
import org.mosaic.hub.domain.repository.HubRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HubPathCacheInitializer {

  private final HubRepository hubRepository;
  private final HubShortPathService shortPathService;

  @EventListener(ApplicationReadyEvent.class)
  public void initHubTransferCache() {
    hubRepository.findAll()
        .forEach(hub -> shortPathService.findShortestPaths(hub.getUuid()));
  }
}
