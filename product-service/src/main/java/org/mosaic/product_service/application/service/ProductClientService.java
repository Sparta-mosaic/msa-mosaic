package org.mosaic.product_service.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.product_service.application.dtos.CompanyHubUuidDto;
import org.mosaic.product_service.domain.repository.ProductRepository;
import org.mosaic.product_service.presentaion.dtos.OrderProductUuidRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductClientService {

  private final ProductRepository productRepository;

  public CompanyHubUuidDto getCompanyHubId(OrderProductUuidRequest req) {

    return CompanyHubUuidDto.builder().build();
  }
}
