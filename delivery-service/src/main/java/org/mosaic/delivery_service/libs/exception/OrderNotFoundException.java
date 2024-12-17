package org.mosaic.delivery_service.libs.exception;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(String orderUuid) {
    super("주문을 찾을 수 없습니다. 주문 UUID: " + orderUuid);
  }
}
