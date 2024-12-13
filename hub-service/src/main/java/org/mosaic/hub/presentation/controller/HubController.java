package org.mosaic.hub.presentation.controller;

import static org.mosaic.hub.libs.util.ApiResponseUtils.ok;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dto.HubPageResponse;
import org.mosaic.hub.application.dto.HubResponse;
import org.mosaic.hub.application.service.HubQueryService;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HubController {

  private final HubQueryService hubQueryService;

  @GetMapping("/api/v1/hubs/{hubUuid}")
  public ResponseEntity<CommonResponse<HubResponse>> getHub(
      @PathVariable String hubUuid) {
    return ok(hubQueryService.getHub(hubUuid));
  }

  @GetMapping("/api/v1/hubs")
  public ResponseEntity<CommonResponse<HubPageResponse>> searchHub(
      @QuerydslPredicate(root = Hub.class) Predicate predicate,
      @PageableDefault(sort = {"createdAt", "updatedAt"}, direction = Direction.DESC) Pageable pageable) {
    return ok(hubQueryService.searchHub(predicate, pageable));
  }
}
