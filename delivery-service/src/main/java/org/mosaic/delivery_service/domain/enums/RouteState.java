package org.mosaic.delivery_service.domain.enums;

public enum RouteState {
  PENDING("허브이동대기중"),
  IN_TRANSIT_TO_HUB("허브이동중"),
  AT_ARRIVAL_HUB("목적지허브도착"),
  OUT_FOR_DELIVERY("출고완료"),
  IN_TRANSIT_TO_COMPANY("업체배송중"),
  DELIVERED("배송완료");
  final String description;

  RouteState(String description) {
    this.description = description;
  }
}
