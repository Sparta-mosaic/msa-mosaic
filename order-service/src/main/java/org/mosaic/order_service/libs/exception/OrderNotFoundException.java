package org.mosaic.order_service.libs.exception;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(String uuid) {
    super("해당 %s로 주문을 찾을 수 없습니다".formatted(uuid));
  }
}
