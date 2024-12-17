package org.mosaic.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.CreateDeliveryDriverResponse;
import org.mosaic.auth.application.dtos.CreateDeliveryDriverService;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriver;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;
import org.mosaic.auth.domain.repository.DeliveryDriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryDriverCommandService {

  private final DeliveryDriverRepository deliveryDriverRepository;

  public CreateDeliveryDriverResponse createDeliveryDriver(
      String userUuid, CreateDeliveryDriverService request) {
    DeliveryDriver deliveryDriver = DeliveryDriver.create(
        request.getUserId(), request.getHubUuid(),
        request.getSlackEmail(),
        request.getDeliveryDriverType(),
        getNextDeliveryOrder(request.getDeliveryDriverType()));
    deliveryDriver.createdBy(userUuid);
    return CreateDeliveryDriverResponse.from(deliveryDriverRepository.save(deliveryDriver));
  }

  private int getNextDeliveryOrder(DeliveryDriverType deliveryDriverType) {
    return deliveryDriverRepository.countByType(deliveryDriverType) +1;
  }
}
