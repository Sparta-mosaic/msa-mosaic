package org.mosaic.order_service.application.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.order_service.domain.entity.Order;

@Getter
public class ProductDeductDto {
  String productUuid;
  Long quantity;

  @Builder
  private ProductDeductDto(String productUuid, Long quantity) {
    this.productUuid = productUuid;
    this.quantity = quantity;
  }

  public static List<ProductDeductDto> of(Order order) {
    return order.getOrderDetails().stream()
        .map(
            detail ->
                ProductDeductDto.builder()
                    .productUuid(detail.getProductId())
                    .quantity(detail.getQuantity())
                    .build())
        .toList();
  }
}
