package org.mosaic.delivery_service.apllication.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.mosaic.delivery_service.domain.entity.delivery.DeliveryInfo;
import org.mosaic.delivery_service.infrastructure.client.OrderFeignClient;
import org.mosaic.delivery_service.infrastructure.client.dtos.OrderInfoDto;
import org.mosaic.delivery_service.libs.exception.OrderNotFoundException;
import org.mosaic.delivery_service.libs.util.MosaicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderClientService {

  private final OrderFeignClient orderFeignClient;

  public DeliveryInfo getDeliveryInfo(String orderUuid) {
    // Todo 디파트
    OrderInfoDto orderInfoDto = getOrderHubPaths(orderUuid);

    return DeliveryInfo.builder()
        .orderDeliveryUuid(UUID.randomUUID().toString())
        .orderId(orderUuid)
        .build();
  }

  public OrderInfoDto getOrderHubPaths(String orderUuid) {
    return Optional.ofNullable(orderFeignClient.getOrderInfo(orderUuid))
        .map(ResponseEntity::getBody)
        .map(MosaicResponse::getResponse)
        .orElseThrow(() -> new OrderNotFoundException(orderUuid));
  }
}
