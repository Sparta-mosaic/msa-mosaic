package org.mosaic.order_service.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.order_service.application.dtos.CreateOrderDto;
import org.mosaic.order_service.application.dtos.CreateOrderResponse;
import org.mosaic.order_service.application.dtos.ProductDeductDto;
import org.mosaic.order_service.application.mapper.OrderMapper;
import org.mosaic.order_service.domain.entity.Order;
import org.mosaic.order_service.domain.repository.OrderRepository;
import org.mosaic.order_service.infrastructure.message.ProductDeductProducer;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j(topic = "OrderCommandService")
public class OrderCommandService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final ProductDeductProducer productDeductProducer;

  public CreateOrderResponse createOrder(CreateOrderDto dto) {
    Order newOrder = orderMapper.CreateOrderDtoToEntity(dto);
    orderRepository.save(newOrder);

    log.info("orderId: {}", newOrder.getOrderId());

    productDeductProducer.send(
        "PRODUCT_DEDUCT_QUANTITY", newOrder.getOrderUuid(), ProductDeductDto.of(newOrder));

    return CreateOrderResponse.of(newOrder);
  }
}
