package org.mosaic.product_service.application.mapper;

import org.mosaic.product_service.application.dtos.CreateProductDto;
import org.mosaic.product_service.domain.entity.Product;
import org.mosaic.product_service.domain.entity.enums.StockType;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public Product productDtoToEntity(CreateProductDto dto){
			Product product = Product.builder()
				.companyId(dto.getCompanyId())
				.productHubId(dto.getProductHubId())
				.productName(dto.getProductName())
				.productPrice(dto.getProductPrice())
				.productDescription(dto.getProductDescription())
				.stockHistories(dto.getStockQuantity())
				.build();
			product.addStockHistory(dto.getStockQuantity(), StockType.INBOUND);
			return product;
	}

}
