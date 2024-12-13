package org.mosaic.hub.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dto.CreateHubRequest;
import org.mosaic.hub.application.dto.CreateHubResponse;
import org.mosaic.hub.application.dto.UpdateHubRequest;
import org.mosaic.hub.application.dto.UpdateHubResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.repository.HubRepository;
import org.mosaic.hub.libs.exception.CustomException;
import org.springframework.http.HttpStatus;
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

  public UpdateHubResponse updateHub(String hubUuid, UpdateHubRequest request) {
    Hub hub = getHubByUuid(hubUuid);
    hub.update(
        request.getManagerId(),
        request.getName(), request.getAddress(),
        request.getLatitude(), request.getLongitude());

    return UpdateHubResponse.from(hub);
  }

  private Hub getHubByUuid(String hubUuid) {
    return hubRepository.findByUuid(hubUuid).orElseThrow(
        () -> new CustomException(HttpStatus.NOT_FOUND, "존재하지 않는 허브입니다."));
  }
}
