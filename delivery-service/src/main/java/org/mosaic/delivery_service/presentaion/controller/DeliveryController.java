package org.mosaic.delivery_service.presentaion.controller;

import lombok.RequiredArgsConstructor;
import org.mosaic.delivery_service.apllication.service.DeliveryMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hub")
public class DeliveryController {
  private final DeliveryMessageService deliveryMessageService;

  @PostMapping
  public void getHub(@RequestBody Testdto testdto) {
    deliveryMessageService.processDelivery(
        testdto.getOrderId(), testdto.getUserId(), testdto.getCompanyHubUuidDtos());
  }
}
