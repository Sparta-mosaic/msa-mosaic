package org.mosaic.order_service.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.order_service.application.dtos.CreateOrderDto;
import org.mosaic.order_service.application.dtos.CreateOrderResponse;
import org.mosaic.order_service.application.mapper.OrderMapper;
import org.mosaic.order_service.domain.entity.Order;
import org.mosaic.order_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;

  public CreateOrderResponse createOrder(CreateOrderDto dto) {
    Order newOrder = orderMapper.CreateOrderDtoToEntity(dto);
    orderRepository.save(newOrder);

    return CreateOrderResponse.of(newOrder);
  }
}
