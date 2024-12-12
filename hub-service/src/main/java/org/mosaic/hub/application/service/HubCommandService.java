package org.mosaic.hub.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dto.CreateHubRequest;
import org.mosaic.hub.application.dto.CreateHubResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.repository.HubRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HubCommandService {

  private final HubRepository hubRepository;

  public CreateHubResponse createHub(CreateHubRequest request) {
    Hub hub = hubRepository.save(Hub.createHub(
        request.getManagerId(), request.getName(), request.getAddress(),
        request.getLatitude(), request.getLongitude()));

    return CreateHubResponse.from(hub);
  }
}
