package org.mosaic.delivery_service.apllication.dtos;

import lombok.Builder;
import lombok.Getter;
import org.mosaic.delivery_service.domain.entity.delivery.Delivery;

@Getter
public class DeliveryRouteDto {
  private Delivery delivery;
  private String departureHubUuid;
  private String arrivalHubUuid;

  @Builder
  private DeliveryRouteDto(Delivery delivery, String departureHubUuid, String arrivalHubUuid) {
    this.delivery = delivery;
    this.departureHubUuid = departureHubUuid;
    this.arrivalHubUuid = arrivalHubUuid;
  }
}
