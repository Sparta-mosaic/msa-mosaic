package org.mosaic.order_service.presentation.controller;

import static org.mosaic.order_service.libs.util.ApiResponseUtils.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mosaic.order_service.application.dtos.CreateOrderResponse;
import org.mosaic.order_service.application.dtos.OrderResponse;
import org.mosaic.order_service.application.service.OrderCommandService;
import org.mosaic.order_service.application.service.OrderQueryService;
import org.mosaic.order_service.libs.util.MosaicResponse;
import org.mosaic.order_service.presentation.dtos.CreateOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
  private final OrderCommandService orderCommandService;
  private final OrderQueryService orderQueryService;

  @PostMapping()
  public ResponseEntity<MosaicResponse<CreateOrderResponse>> createOrder(
      @Valid @RequestBody CreateOrderRequest req) {
    return created(orderCommandService.createOrder(req.toDto()));
  }

  @GetMapping("/{productUuid}")
  public ResponseEntity<MosaicResponse<OrderResponse>> getProduct(
      @PathVariable String productUuid) {
    return ok(orderQueryService.getProductResponse(productUuid));
  }
}
