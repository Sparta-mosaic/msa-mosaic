package org.mosaic.order_service.application.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailResponse {
  private final String orderDetailUuid;
  private final String productId;
  private final String productName;
  private final Long quantity;
  private final Long unitPrice;

  @Builder
  private OrderDetailResponse(
      String orderDetailUuid, String productId, String productName, Long quantity, Long unitPrice) {
    this.orderDetailUuid = orderDetailUuid;
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
}
