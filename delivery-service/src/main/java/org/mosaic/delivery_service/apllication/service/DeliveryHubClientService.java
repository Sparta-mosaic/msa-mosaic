package org.mosaic.delivery_service.apllication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mosaic.delivery_service.domain.entity.delivery.DeliveryTotalTimeDistance;
import org.mosaic.delivery_service.domain.entity.deliveryRoute.DeliveryRouteInfo;
import org.mosaic.delivery_service.infrastructure.client.HubFeignClient;
import org.mosaic.delivery_service.infrastructure.client.dtos.CommonResponse;
import org.mosaic.delivery_service.infrastructure.client.dtos.GetHubPathResponse;
import org.mosaic.delivery_service.infrastructure.client.dtos.HubPathResponse;
import org.mosaic.delivery_service.libs.exception.PathNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryHubClientService {

  private final HubFeignClient hubFeignClient;

  public DeliveryTotalTimeDistance getDeliveryTotalTimeDistance(String sup, String rec) {
    List<HubPathResponse> hubPaths = getHubPath(sup, rec);

    if (hubPaths == null || hubPaths.isEmpty()) {
      throw new IllegalArgumentException("Hub paths list is empty or null");
    }

    HubPathResponse lastHub = hubPaths.get(hubPaths.size() - 1);

    return DeliveryTotalTimeDistance.builder()
        .totalDistance((long) lastHub.getDistance())
        .totalElapsedTime(Double.parseDouble(String.valueOf(lastHub.getTime())))
        .build();
  }

  public List<DeliveryRouteInfo> createDeliveryRouteInfos(
      String departureHubUuid, String arrivalHubUuid) {
    List<HubPathResponse> hubPaths = getHubPath(departureHubUuid, arrivalHubUuid);

    List<DeliveryRouteInfo> deliveryRouteDtos = new ArrayList<>();
    for (int i = 0; i < hubPaths.size() - 1; i++) {
      HubPathResponse currentHub = hubPaths.get(i);
      HubPathResponse nextHub = hubPaths.get(i + 1);

      DeliveryRouteInfo routeDto =
          DeliveryRouteInfo.builder()
              .sequence((long) (i + 1))
              .departureHubId(currentHub.getHubUuid())
              .departureHubName(currentHub.getHubName())
              .arrivalHubId(nextHub.getHubUuid())
              .arrivalHubName(nextHub.getHubName())
              .estimatedDistance(nextHub.getDistance() - currentHub.getDistance())
              .estimatedElapsedTime(
                  Long.valueOf(nextHub.getTime()) - Long.valueOf(currentHub.getTime()))
              .build();

      deliveryRouteDtos.add(routeDto);
    }
    return deliveryRouteDtos;
  }

  private List<HubPathResponse> getHubPath(String departureHubUuid, String arrivalHubUuid) {
    return Optional.ofNullable(hubFeignClient.getHubPath(departureHubUuid, arrivalHubUuid))
        .map(ResponseEntity::getBody)
        .map(CommonResponse::getResponse)
        .map(GetHubPathResponse::getHubPaths)
        .orElseThrow(
            () ->
                new PathNotFoundException(
                    "Hub path not found for departure: "
                        + departureHubUuid
                        + " and arrival: "
                        + arrivalHubUuid));
  }
}
