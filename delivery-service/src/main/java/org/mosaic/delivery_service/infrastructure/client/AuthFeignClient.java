package org.mosaic.delivery_service.infrastructure.client;

import org.mosaic.delivery_service.infrastructure.client.dtos.CompanyRequest;
import org.mosaic.delivery_service.infrastructure.client.dtos.DeliveryShippingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-Service")
public interface AuthFeignClient {

  @PostMapping("/api/v1/internal/deliverys")
  DeliveryShippingResponse getDeliveryShipping(@RequestBody CompanyRequest companyRequest);
}
