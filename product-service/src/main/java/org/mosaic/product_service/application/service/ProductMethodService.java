package org.mosaic.product_service.application.service;

import org.mosaic.product_service.application.dtos.CreateProductDto;
import org.mosaic.product_service.application.dtos.CreateProductResponse;
import org.mosaic.product_service.application.mapper.ProductMapper;
import org.mosaic.product_service.domain.entity.Product;
import org.mosaic.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMethodService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	public CreateProductResponse createProduct(CreateProductDto request) {
		Product newProduct = productMapper.productDtoToEntity(request);
		productRepository.save(newProduct);
		return CreateProductResponse.of(newProduct);
	}

}
