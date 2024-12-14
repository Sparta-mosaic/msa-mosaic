package org.mosaic.product_service.libs.exception;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String uuid) {
		super("해당 %s로 상품을 찾을 수 없습니다".formatted(uuid));
	}
}

