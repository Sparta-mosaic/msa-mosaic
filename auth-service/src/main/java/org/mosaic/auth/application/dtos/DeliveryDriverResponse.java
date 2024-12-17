package org.mosaic.auth.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriver;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class DeliveryDriverResponse {

  private Long deliveryDriverId;
  private String deliveryDriverUuid;
  private String slackEmail;
  private DeliveryDriverType type;

  public static DeliveryDriverResponse from(DeliveryDriver deliveryDriver) {
    return DeliveryDriverResponse.builder()
        .deliveryDriverId(deliveryDriver.getId())
        .deliveryDriverUuid(deliveryDriver.getUuid())
        .slackEmail(deliveryDriver.getSlackEmail())
        .type(deliveryDriver.getType())
        .build();
  }
}
