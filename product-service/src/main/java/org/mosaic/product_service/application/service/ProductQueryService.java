package org.mosaic.product_service.application.service;

import org.mosaic.product_service.application.dtos.ProductResponse;
import org.mosaic.product_service.domain.entity.Product;
import org.mosaic.product_service.domain.repository.ProductRepository;
import org.mosaic.product_service.libs.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService {
  private final ProductRepository productRepository;

  public Product findProductUuid(String uuid) {
    return productRepository
        .findByProductUuid(uuid)
        .orElseThrow(() -> new ProductNotFoundException(uuid));
  }

  public ProductResponse getProductResponse(String uuid) {
    return ProductResponse.of(findProductUuid(uuid));
  }
}
