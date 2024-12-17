package org.mosaic.hub.presentation.controller;

import static org.mosaic.hub.libs.util.ApiResponseUtils.ok;

import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dtos.GetHubPathResponse;
import org.mosaic.hub.application.service.HubQueryService;
import org.mosaic.hub.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientHubController {

  private final HubQueryService hubQueryService;

  @GetMapping("/api/v1/internal/hub-paths")
  public ResponseEntity<CommonResponse<GetHubPathResponse>> getHubPath(
      @RequestParam String departureHubUuid, @RequestParam String arrivalHubUuid) {
    return ok(hubQueryService.getHubPath(departureHubUuid, arrivalHubUuid));
  }
}
