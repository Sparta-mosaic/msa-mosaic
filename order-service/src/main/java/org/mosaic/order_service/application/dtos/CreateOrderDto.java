package org.mosaic.order_service.application.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderDto {
  private final String supplierCompanyId;
  private final String receiverCompanyId;
  private final List<OrderDetailDto> orderDetails;

  @Getter
  @Builder
  public static class OrderDetailDto {
    private final String productId;
    private final String productName;
    private final Long quantity;
    private final Long unitPrice;
  }
}
