package org.mosaic.auth.presentation.controller.delivery_driver;


import static org.mosaic.auth.libs.util.ApiResponseUtils.ok;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.DeliveryDriverResponse;
import org.mosaic.auth.application.service.DeliveryDriverCommandService;
import org.mosaic.auth.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientDeliveryDriverController {

  private final DeliveryDriverCommandService deliveryDriverCommandService;

  @PreAuthorize("hasAnyRole('MASTER')")
  @GetMapping("/api/v1/internal/delivery-drivers/assigned-driver")
  public ResponseEntity<CommonResponse<DeliveryDriverResponse>> assignHubDeliveryDriver() {
    return ok(deliveryDriverCommandService.assignHubDeliveryDriver());
  }

  @PreAuthorize("hasAnyRole('MASTER')")
  @GetMapping("/api/v1/internal/delivery-drivers/company/{hubUuid}/assigned-driver")
  public ResponseEntity<CommonResponse<DeliveryDriverResponse>> assignHubDeliveryDriver(
      @PathVariable String hubUuid) {
    return ok(deliveryDriverCommandService.assignCompanyDeliveryDriver(hubUuid));
  }
}
