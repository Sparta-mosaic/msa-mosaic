package org.mosaic.hub.presentation.controller;

import static org.mosaic.hub.libs.util.ApiResponseUtils.created;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dto.CreateHubRequest;
import org.mosaic.hub.application.dto.CreateHubResponse;
import org.mosaic.hub.application.service.HubCommandService;
import org.mosaic.hub.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminHubController {

  private final HubCommandService hubCommandService;

  @PostMapping("/api/v1/admin/hubs")
  public ResponseEntity<CommonResponse<CreateHubResponse>> createHub(
      @Valid @RequestBody CreateHubRequest request) {
    return created(hubCommandService.createHub(request));
  }
}
