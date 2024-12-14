package org.mosaic.product_service.application.service;

import org.mosaic.product_service.application.dtos.CreateProductDto;
import org.mosaic.product_service.application.dtos.CreateProductResponse;
import org.mosaic.product_service.application.mapper.ProductMapper;
import org.mosaic.product_service.domain.entity.Product;
import org.mosaic.product_service.domain.repository.ProductRepository;
import org.mosaic.product_service.infrastructure.jpa.ProductStockHistoryRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

	private final ProductRepository productRepository;
	private final ProductStockHistoryRepository productStockHistoryRepository;
	private final ProductMapper productMapper;
	private final ProductQueryService productQueryService;

	public CreateProductResponse createProduct(CreateProductDto request) {
		Product newProduct = productMapper.productDtoToEntity(request);
		productRepository.save(newProduct);
		return CreateProductResponse.of(newProduct);
	}

	public void deleteProduct(String userUuid, String productUuid) {
		Product product = productQueryService.findProductUuid(productUuid);
		product.getStockHistories().forEach(history -> history.softDelete(userUuid));
		product.softDelete(userUuid);
		productRepository.save(product);
	}
}
