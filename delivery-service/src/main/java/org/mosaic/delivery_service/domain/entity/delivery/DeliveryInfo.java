package org.mosaic.delivery_service.domain.entity.delivery;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryInfo {

  @Column(
      name = "DELIVERY_UUID",
      updatable = false,
      nullable = false,
      columnDefinition = "VARCHAR(36)")
  private String orderDeliveryUuid;

  @Column(name = "ORDER_ID", nullable = false)
  private String orderId;

  @Column(name = "DEPARTURE_HUB_ID", nullable = false)
  private String departureHubId;

  @Column(name = "ARRIVAL_HUB_ID", nullable = false)
  private String arrivalHubId;

  @Builder
  public DeliveryInfo(
      String orderDeliveryUuid, String orderId, String departureHubId, String arrivalHubId) {
    this.orderDeliveryUuid = orderDeliveryUuid;
    this.orderId = orderId;
    this.departureHubId = departureHubId;
    this.arrivalHubId = arrivalHubId;
  }
}
