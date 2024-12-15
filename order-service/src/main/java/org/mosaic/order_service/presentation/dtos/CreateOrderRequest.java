package org.mosaic.order_service.presentation.dtos;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.order_service.application.dtos.CreateOrderDto;

@Getter
@AllArgsConstructor
public class CreateOrderRequest {
  private String supplierCompanyId;
  private String receiverCompanyId;
  private List<OrderDetailRequest> orderDetails;

  @Getter
  @AllArgsConstructor
  public static class OrderDetailRequest {
    private String productId;
    private String productName;
    private Long quantity;
    private Long unitPrice;
  }

  public CreateOrderDto toDto() {
    List<CreateOrderDto.OrderDetailDto> detailDtos =
        this.orderDetails.stream()
            .map(
                detail ->
                    CreateOrderDto.OrderDetailDto.builder()
                        .productId(detail.getProductId())
                        .productName(detail.getProductName())
                        .quantity(detail.getQuantity())
                        .unitPrice(detail.getUnitPrice())
                        .build())
            .collect(Collectors.toList());

    return CreateOrderDto.builder()
        .supplierCompanyId(this.supplierCompanyId)
        .receiverCompanyId(this.receiverCompanyId)
        .orderDetails(detailDtos)
        .build();
  }
}
