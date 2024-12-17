package org.mosaic.auth.infrastructure;

import org.mosaic.auth.application.dtos.company.HubResponse;
import org.mosaic.auth.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service")
public interface HubClient {

  @GetMapping("/api/v1/internal/hubs/{hubUuid}")
  CommonResponse<HubResponse> getHub(@PathVariable String hubUuid);
}
