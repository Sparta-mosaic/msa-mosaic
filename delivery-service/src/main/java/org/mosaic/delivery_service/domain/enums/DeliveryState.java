package org.mosaic.delivery_service.domain.enums;

public enum DeliveryState {
  PENDING("배송대기"),
  REQUESTED("배송요청"),
  IN_TRANSIT("배송중"),
  DELIVERED("배송완료"),
  CONFIRMED("배송확정");

  final String description;

  DeliveryState(String description) {
    this.description = description;
  }
}
