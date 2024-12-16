package org.mosaic.order_service.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.order_service.application.dtos.OrderResponse;
import org.mosaic.order_service.domain.entity.Order;
import org.mosaic.order_service.domain.repository.OrderRepository;
import org.mosaic.order_service.libs.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

  private final OrderRepository orderRepository;

  public Order findOrderUuid(String uuid) {
    return orderRepository
        .findByOrderUuid(uuid)
        .orElseThrow(() -> new OrderNotFoundException(uuid));
  }

  public OrderResponse getProductResponse(String uuid) {
    return OrderResponse.of(findOrderUuid(uuid));
  }
}
