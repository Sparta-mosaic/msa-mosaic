package org.mosaic.product_service.presentaion.controller;

import lombok.RequiredArgsConstructor;
import org.mosaic.product_service.application.dtos.CompanyHubUuidDto;
import org.mosaic.product_service.application.service.ProductClientService;
import org.mosaic.product_service.presentaion.dtos.OrderProductUuidRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/internal/products")
public class ClientProductController {

  private final ProductClientService productClientService;

  @GetMapping
  public CompanyHubUuidDto getProductHubId(@RequestBody OrderProductUuidRequest req) {
    return productClientService.getCompanyHubId(req);
  }
}
