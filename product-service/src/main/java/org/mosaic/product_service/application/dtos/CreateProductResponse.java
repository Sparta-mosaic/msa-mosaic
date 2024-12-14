package org.mosaic.product_service.application.dtos;

import org.mosaic.product_service.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductResponse {
	private String productUuid;
	private String companyId;
	private String productHubId;
	private String productName;
	private Long productPrice;
	private String productDescription;
	private Long stockQuantity;

	@Builder
	private CreateProductResponse(String productUuid, String companyId,
		String productHubId, String productName, Long productPrice,
		String productDescription, Long stockQuantity) {
		this.productUuid = productUuid;
		this.companyId = companyId;
		this.productHubId = productHubId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.stockQuantity = stockQuantity;
	}

	public static CreateProductResponse of(Product product){
		return CreateProductResponse.builder()
			.productUuid(product.getProductUuid())
			.companyId(product.getCompanyId())
			.productHubId(product.getProductHubId())
			.productName(product.getProductName())
			.productPrice(product.getProductPrice())
			.productDescription(product.getProductDescription())
			.stockQuantity(product.getStockQuantity())
			.build();
	}
}
