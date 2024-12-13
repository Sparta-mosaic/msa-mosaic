package org.mosaic.hub.presentation.controller;

import static org.mosaic.hub.libs.constant.HttpHeaderConstants.HEADER_USER_ID;
import static org.mosaic.hub.libs.util.ApiResponseUtils.created;
import static org.mosaic.hub.libs.util.ApiResponseUtils.noContent;
import static org.mosaic.hub.libs.util.ApiResponseUtils.ok;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dtos.CreateHubResponse;
import org.mosaic.hub.application.dtos.UpdateHubResponse;
import org.mosaic.hub.application.service.HubCommandService;
import org.mosaic.hub.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.hub.presentation.dtos.CreateHubRequest;
import org.mosaic.hub.presentation.dtos.UpdateHubRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminHubController {

  private final HubCommandService hubCommandService;

  @PostMapping("/api/v1/admin/hubs")
  public ResponseEntity<CommonResponse<CreateHubResponse>> createHub(
      @Valid @RequestBody CreateHubRequest request) {
    return created(hubCommandService.createHub(request.toService()));
  }

  @PutMapping("/api/v1/admin/hubs/{hubUuid}")
  public ResponseEntity<CommonResponse<UpdateHubResponse>> updateHub(
      @PathVariable String hubUuid,
      @Valid @RequestBody UpdateHubRequest request) {
    return ok(hubCommandService.updateHub(hubUuid, request.toService()));
  }

  @DeleteMapping("/api/v1/admin/hubs/{hubUuid}")
  public ResponseEntity<CommonResponse<String>> deleteHub(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @PathVariable String hubUuid) {
    hubCommandService.deleteHub(userUuid, hubUuid);
    return noContent();
  }
}
