package org.mosaic.slack.infrastructure;

import org.mosaic.slack.application.dto.ClientUserResponse;
import org.mosaic.slack.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface  AuthClient {

  @GetMapping("/api/v1/internal/auth/{userUuid}")
  ResponseEntity<CommonResponse<ClientUserResponse>> getUser(
      @PathVariable String userUuid);

}
