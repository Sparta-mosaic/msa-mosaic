package org.mosaic.hub.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dtos.CreateHubResponse;
import org.mosaic.hub.application.dtos.CreateHubServiceRequest;
import org.mosaic.hub.application.dtos.CreateHubTransferResponse;
import org.mosaic.hub.application.dtos.CreateHubTransferServiceRequest;
import org.mosaic.hub.application.dtos.HubTransferInfo;
import org.mosaic.hub.application.dtos.UpdateHubResponse;
import org.mosaic.hub.application.dtos.UpdateHubServiceRequest;
import org.mosaic.hub.application.dtos.UpdateHubTransferServiceRequest;
import org.mosaic.hub.application.dtos.UpdateTransferResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.model.HubTransfer;
import org.mosaic.hub.domain.repository.HubRepository;
import org.mosaic.hub.libs.exception.CustomException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HubCommandService {

  private final HubRepository hubRepository;

  @CachePut(cacheNames = "hubCache", key = "#result.hubUuid")
  public CreateHubResponse createHub(CreateHubServiceRequest request) {
    Hub hub = hubRepository.save(Hub.create(
        request.getManagerId(), request.getName(), request.getAddress(),
        request.getLatitude(), request.getLongitude()));

    return CreateHubResponse.from(hub);
  }

  @CachePut(cacheNames = "hubCache", key = "args[0]")
  public UpdateHubResponse updateHub(String hubUuid,
      UpdateHubServiceRequest request) {
    Hub hub = getHubByUuid(hubUuid);
    hub.update(
        request.getManagerId(),
        request.getName(), request.getAddress(),
        request.getLatitude(), request.getLongitude());

    return UpdateHubResponse.from(hub);
  }

  @CacheEvict(cacheNames = "hubCache", key = "args[1]")
  public void deleteHub(String userUuid, String hubUuid) {
    Hub hub = getHubByUuid(hubUuid);
    hub.softDelete(userUuid);
  }

  public CreateHubTransferResponse createHubTransfer(
      String departureHubUuid, CreateHubTransferServiceRequest request) {
    Hub departureHub = getHubByUuid(departureHubUuid);

    List<HubTransfer> hubTransfers = generateHubTransfer(
        departureHub, request.getHubTransferInfoList());

    departureHub.addHubTransfer(hubTransfers);

    return CreateHubTransferResponse.from(hubTransfers);
  }

  public UpdateTransferResponse updateHubTransfer(
      String departureHubUuid, String hubTransferUuid,
      UpdateHubTransferServiceRequest request) {
    Hub departureHub = getHubByUuid(departureHubUuid);
    HubTransfer hubTransfer = findHubTransfer(departureHub, hubTransferUuid);
    hubTransfer.update(request.getEstimatedTime(), request.getEstimatedDistance());

    return UpdateTransferResponse.from(hubTransfer);
  }

  private HubTransfer findHubTransfer(Hub departureHub, String hubTransferUuid) {
    return departureHub.getHubTransfers().stream()
        .filter(hubTransfer -> hubTransfer.getUuid().equals(hubTransferUuid))
        .findFirst()
        .orElseThrow(() -> new CustomException(
            HttpStatus.NOT_FOUND, "존재하지 않는 허브 이동 정보입니다."));
  }

  private List<HubTransfer> generateHubTransfer(
      Hub departureHub, List<HubTransferInfo> hubTransferInfoList) {
    return hubTransferInfoList.stream()
        .map(hubTransferInfo -> HubTransfer.create(
            departureHub, getHubByUuid(hubTransferInfo.getArrivalHubUuid()),
            hubTransferInfo.getEstimatedTime(),
            hubTransferInfo.getEstimatedDistance()))
        .toList();
  }

  private Hub getHubByUuid(String hubUuid) {
    return hubRepository.findByUuid(hubUuid).orElseThrow(
        () -> new CustomException(HttpStatus.NOT_FOUND, "존재하지 않는 허브입니다."));
  }
}
