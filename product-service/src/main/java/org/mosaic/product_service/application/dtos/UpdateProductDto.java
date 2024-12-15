package org.mosaic.product_service.application.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateProductDto {
	private String companyId;
	private String productHubId;
	private String productName;
	private Long productPrice;
	private String productDescription;
	private Long stockQuantity;

	@Builder
	private UpdateProductDto(String companyId, String productHubId, String productName,
		Long productPrice, String productDescription, Long stockQuantity) {
		this.companyId = companyId;
		this.productHubId = productHubId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.stockQuantity = stockQuantity;
	}

	public static UpdateProductDto create(String companyId, String productHubId,
		String productName, String productPrice,
		String productDescription,
		String stockQuantity) {
		return UpdateProductDto.builder()
			.companyId(companyId)
			.productHubId(productHubId)
			.productName(productName)
			.productPrice(Long.valueOf(productPrice))
			.productDescription(productDescription)
			.stockQuantity(Long.valueOf(stockQuantity))
			.build();
	}
}
