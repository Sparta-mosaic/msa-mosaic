package org.mosaic.auth.libs.common;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.common.dtos.CompanyRequest;
import org.mosaic.auth.libs.common.dtos.DeliveryShippingResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/internal/deliverys")
public class DeliveryClientController {

  private final DeliveryClientService deliveryClientService;

  @PostMapping
  public DeliveryShippingResponse getDeliveryShipping(@RequestBody CompanyRequest companyRequest) {
    return deliveryClientService.getDeliveryShipping(companyRequest);
  }
}
