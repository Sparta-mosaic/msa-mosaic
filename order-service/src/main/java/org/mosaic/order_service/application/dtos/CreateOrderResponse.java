package org.mosaic.order_service.application.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.order_service.domain.entity.Order;

@Getter
public class CreateOrderResponse {
  private final OrderInfo orderInfo;
  private final List<OrderDetailResponse> orderDetails;

  @Builder
  private CreateOrderResponse(OrderInfo orderInfo, List<OrderDetailResponse> orderDetails) {
    this.orderInfo = orderInfo;
    this.orderDetails = orderDetails;
  }

  public static CreateOrderResponse of(Order order) {
    List<OrderDetailResponse> detailResponses =
        order.getOrderDetails().stream()
            .map(
                detail ->
                    OrderDetailResponse.builder()
                        .orderDetailUuid(detail.getOrderDetailUuid())
                        .productId(detail.getProductId())
                        .productName(detail.getProductName())
                        .quantity(detail.getQuantity())
                        .unitPrice(detail.getUnitPrice())
                        .build())
            .toList();

    OrderInfo orderInfo =
        new OrderInfo(
            order.getOrderUuid(),
            order.getSupplierCompanyId(),
            order.getReceiverCompanyId(),
            order.getOrderState(),
            order.getOrderDate(),
            order.getTotalAmount(),
            order.getTotalQuantity());

    return CreateOrderResponse.builder().orderInfo(orderInfo).orderDetails(detailResponses).build();
  }
}
