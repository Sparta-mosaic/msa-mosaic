package org.mosaic.product_service.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mosaic.product_service.application.dtos.CreateProductDto;
import org.mosaic.product_service.application.dtos.CreateProductResponse;
import org.mosaic.product_service.application.dtos.UpdateProductDto;
import org.mosaic.product_service.application.dtos.UpdateProductResponse;
import org.mosaic.product_service.application.mapper.ProductMapper;
import org.mosaic.product_service.domain.entity.Product;
import org.mosaic.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCommandService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final ProductQueryService productQueryService;

  public CreateProductResponse createProduct(CreateProductDto req) {
    Product newProduct = productMapper.createProductDtoToEntity(req);
    productRepository.save(newProduct);

    return CreateProductResponse.of(newProduct);
  }

  public void deleteProduct(String userUuid, String productUuid) {

    Product product = productQueryService.findProductUuid(productUuid);
    product.getStockHistories().forEach(history -> history.softDelete(userUuid));
    product.softDelete(userUuid);

    productRepository.save(product);
  }

  public UpdateProductResponse updateProduct(String productUuid, UpdateProductDto req) {

    Product product = productQueryService.findProductUuid(productUuid);
    Product updateProduct = productMapper.updateProductDtoToEntity(product, req);
    productRepository.save(product);

    return UpdateProductResponse.of(updateProduct);
  }
}
