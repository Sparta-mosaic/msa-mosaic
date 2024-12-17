package org.mosaic.order_service.application.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mosaic.order_service.application.dtos.CreateOrderDto;
import org.mosaic.order_service.domain.entity.Order;
import org.mosaic.order_service.domain.entity.OrderDetail;
import org.mosaic.order_service.domain.enums.OrderState;
import org.mosaic.order_service.infrastructure.client.dtos.OrderInfoDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

  public Order CreateOrderDtoToEntity(CreateOrderDto dto) {
    Order order =
        Order.builder()
            .supplierCompanyId(dto.getSupplierCompanyId())
            .receiverCompanyId(dto.getReceiverCompanyId())
            .orderDate(LocalDateTime.now())
            .orderUuid(UUID.randomUUID().toString())
            .orderState(OrderState.PENDING)
            .build();

    List<OrderDetail> orderDetailList =
        dto.getOrderDetails().stream()
            .map(
                detailDto ->
                    OrderDetail.builder()
                        .orderDetailUuid(UUID.randomUUID().toString())
                        .productId(detailDto.getProductId())
                        .productName(detailDto.getProductName())
                        .quantity(detailDto.getQuantity())
                        .unitPrice(detailDto.getUnitPrice())
                        .build())
            .collect(Collectors.toList());

    order.addOrderDetails(orderDetailList);
    order.addOrderStateHistory(OrderState.PENDING);

    return order;
  }

  public OrderInfoDto orderEntityToOrderInfoDto(Order order) {
    return OrderInfoDto.builder()
        .receiverCompanyId(order.getReceiverCompanyId())
        .supplierCompanyId(order.getSupplierCompanyId())
        .orderDate(order.getOrderDate())
        .build();
  }
}
