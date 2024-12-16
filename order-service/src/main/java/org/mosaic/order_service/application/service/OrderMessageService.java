package org.mosaic.order_service.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.order_service.domain.entity.Order;
import org.mosaic.order_service.domain.enums.OrderState;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderMessageService {

  private final OrderQueryService orderQueryService;

  public void changeOrderStateToCreated(String orderUuid) {
    Order order = orderQueryService.findOrderUuid(orderUuid);
    order.addOrderStateHistory(OrderState.CREATED);
  }

  public void changeOrderStateToCancel(String orderUuid) {
    Order order = orderQueryService.findOrderUuid(orderUuid);
    if (!order.getOrderState().equals(OrderState.CANCELLED)) {
      order.addOrderStateHistory(OrderState.CANCELLED);
    }
  }
}
