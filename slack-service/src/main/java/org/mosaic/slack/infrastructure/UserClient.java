package org.mosaic.slack.infrastructure;

import org.mosaic.slack.application.dto.UserFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface UserClient {

  @GetMapping("/api/v1/feign/user/{userId}")
  UserFeignResponse getUser(@PathVariable("userId") Long id);

}
