package org.mosaic.auth.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriver;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class CreateDeliveryDriverResponse {

  private Long deliveryDriverId;
  private String deliveryDriverUuid;
  private String hubUuid;
  private DeliveryDriverType type;
  private int deliveryOrder;
  private LocalDateTime createdAt;
  private String createdBy;
  private boolean isPublic;

  public static CreateDeliveryDriverResponse from(DeliveryDriver deliveryDriver) {
    return CreateDeliveryDriverResponse.builder()
        .deliveryDriverId(deliveryDriver.getId())
        .deliveryDriverUuid(deliveryDriver.getUuid())
        .hubUuid(deliveryDriver.getHubUuid())
        .type(deliveryDriver.getType())
        .deliveryOrder(deliveryDriver.getDeliveryOrder())
        .createdAt(deliveryDriver.getCreatedAt())
        .createdBy(deliveryDriver.getCreatedBy())
        .isPublic(deliveryDriver.isPublic())
        .build();
  }
}
