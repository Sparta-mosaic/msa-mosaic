package org.mosaic.auth.application.service;

import static org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType.COMPANY;
import static org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType.HUB;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.CreateDeliveryDriverResponse;
import org.mosaic.auth.application.dtos.CreateDeliveryDriverService;
import org.mosaic.auth.application.dtos.DeliveryDriverResponse;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriver;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;
import org.mosaic.auth.domain.repository.DeliveryDriverRepository;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
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

  public DeliveryDriverResponse assignHubDeliveryDriver() {
    DeliveryDriver nextDriver = assignDeliveryDriver(HUB, null);
    return DeliveryDriverResponse.from(nextDriver);
  }

  public DeliveryDriverResponse assignCompanyDeliveryDriver(String hubUuid) {
    DeliveryDriver nextDriver = assignDeliveryDriver(COMPANY, hubUuid);
    return DeliveryDriverResponse.from(nextDriver);
  }

  private DeliveryDriver assignDeliveryDriver(DeliveryDriverType type, String hubUuid) {
    DeliveryDriver currentDriver = deliveryDriverRepository
        .findLatestDriver(type, hubUuid)
        .orElse(null);

    DeliveryDriver nextDriver;

    if (currentDriver != null) {
      nextDriver = deliveryDriverRepository.findNextDriver(type, hubUuid, currentDriver.getDeliveryOrder())
          .orElseGet(() -> findFirstDriver(type, hubUuid));

      currentDriver.updateLatestStatus(false);
      deliveryDriverRepository.save(currentDriver);
    } else {
      nextDriver = findFirstDriver(type, hubUuid);
    }

    nextDriver.updateLatestStatus(true);
    return nextDriver;
  }

  private DeliveryDriver findFirstDriver(DeliveryDriverType type, String hubUuid) {
    return deliveryDriverRepository.findFirstDriver(type, hubUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.DELIVERY_DRIVER_NOT_FOUND));
  }

  private int getNextDeliveryOrder(DeliveryDriverType deliveryDriverType) {
    return deliveryDriverRepository.countByType(deliveryDriverType) +1;
  }
}
