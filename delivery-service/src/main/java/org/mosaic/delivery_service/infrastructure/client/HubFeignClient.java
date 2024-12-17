package org.mosaic.delivery_service.infrastructure.client;

import org.mosaic.delivery_service.infrastructure.client.dtos.CommonResponse;
import org.mosaic.delivery_service.infrastructure.client.dtos.GetHubPathResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hub-Service")
public interface HubFeignClient {

  @GetMapping("/api/v1/internal/hub-paths")
  ResponseEntity<CommonResponse<GetHubPathResponse>> getHubPath(
      @RequestParam("departureHubUuid") String departureHubUuid,
      @RequestParam("arrivalHubUuid") String arrivalHubUuid);
}
