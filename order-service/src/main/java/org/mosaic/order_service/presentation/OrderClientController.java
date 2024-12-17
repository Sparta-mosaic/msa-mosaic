package org.mosaic.order_service.presentation;

import static org.mosaic.order_service.libs.util.ApiResponseUtils.*;

import lombok.RequiredArgsConstructor;
import org.mosaic.order_service.application.service.OrderQueryService;
import org.mosaic.order_service.infrastructure.client.dtos.OrderInfoDto;
import org.mosaic.order_service.libs.util.MosaicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/internal/orders")
public class OrderClientController {

  private final OrderQueryService orderQueryService;

  @GetMapping("/{orderUuid}")
  public ResponseEntity<MosaicResponse<OrderInfoDto>> getOrderInfo(@PathVariable String orderUuid) {
    return ok(orderQueryService.findOrderInfo(orderUuid));
  }
}
