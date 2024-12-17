package org.mosaic.delivery_service.infrastructure.client;

import org.mosaic.delivery_service.infrastructure.client.dtos.OrderInfoDto;
import org.mosaic.delivery_service.libs.util.MosaicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-Service")
public interface OrderFeignClient {

  @GetMapping("/api/v1/internal/orders/{orderUuid}")
  ResponseEntity<MosaicResponse<OrderInfoDto>> getOrderInfo(@PathVariable String orderUuid);
}
