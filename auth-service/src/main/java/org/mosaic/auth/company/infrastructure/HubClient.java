package org.mosaic.auth.company.infrastructure;

import org.mosaic.auth.company.application.dtos.HubResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service", url = "http://localhost:19095")
public interface HubClient {
  @GetMapping("/api/v1/feign/hubs/{hubUuid}")
  HubResponse getHub(@PathVariable String hubUuid);

}
